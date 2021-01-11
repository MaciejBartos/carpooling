package pl.lodz.p.edu.carpooling.CMS.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDetailsDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountRolesDTO;
import pl.lodz.p.edu.carpooling.CMS.request.ChangePasswordRequest;
import pl.lodz.p.edu.carpooling.CMS.request.UpdateAccountRequest;
import pl.lodz.p.edu.carpooling.CMS.response.AccountDetailsResponse;
import pl.lodz.p.edu.carpooling.CMS.response.model.AccountDetailsForList;
import pl.lodz.p.edu.carpooling.CMS.service.converter.AccountToAccountDetailsDTOConverter;
import pl.lodz.p.edu.carpooling.CMS.service.converter.AccountToAccountDetailsForListConverter;
import pl.lodz.p.edu.carpooling.CMS.service.converter.AccountToAccountDetailsResponseConverter;
import pl.lodz.p.edu.carpooling.exception.account.AccountException;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.entity.Address;
import pl.lodz.p.edu.carpooling.persistence.entity.PersonalData;
import pl.lodz.p.edu.carpooling.persistence.entity.Role;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnum;
import pl.lodz.p.edu.carpooling.persistence.repository.AccountRepository;
import pl.lodz.p.edu.carpooling.persistence.repository.RoleRepository;
import pl.lodz.p.edu.carpooling.security.request.SignUpRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class AccountService {

    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public void registerAccount(SignUpRequest signUpRequest) {
        Role userRole = roleRepository.findRoleByName(RoleEnum.ROLE_USER);
        Account account = prepareAccountToRegister(signUpRequest);
        account.setActive(true);
        account.setPassword(encoder.encode(signUpRequest.getPassword()));
        account.getRoles().add(userRole);
        accountRepository.save(account);
    }

    public AccountDetailsDTO getAccountById(Long id) {
        return AccountToAccountDetailsDTOConverter.convert(accountRepository.findById(id));
    }

    public AccountDetailsResponse getAccountDetailsByLogin(String login) {
        return AccountToAccountDetailsResponseConverter.convert(accountRepository.findByLogin(login));
    }

    public List<AccountDetailsForList> getAccounts(String searchCriteria) {
        List<Account> accounts = accountRepository.findAllWithSearchCriteria(searchCriteria);
        return accounts.stream()
                .map(AccountToAccountDetailsForListConverter::convert)
                .collect(Collectors.toList());
    }

    public void updateAccount(UpdateAccountRequest accountDto) {
        Account account = accountRepository.findById(accountDto.getId());
        account.setEmail(accountDto.getEmail());
        account.setVersion(accountDto.getVersion());
        accountRepository.update(account);
    }

    public void updateRoles(AccountRolesDTO accountRolesDTO) {
        List<RoleEnum> roles = accountRolesDTO.getRoles().stream()
                .map(RoleEnum::fromStringRoleView)
                .collect(Collectors.toList());
        List<Role> rolesDb = roles.stream()
                .map(roleRepository::findRoleByName)
                .collect(Collectors.toList());
        Account account = accountRepository.findById(accountRolesDTO.getId());
        account.setRoles(rolesDb);
        accountRepository.save(account);
    }

    public void updateAccountPassword(ChangePasswordRequest changePasswordRequest) {
        Account account = accountRepository.findById(Long.valueOf(changePasswordRequest.getAccountId()));
        if (!encoder.matches(changePasswordRequest.getOldPassword(), account.getPassword())) {
            throw AccountException.wrongCurrentPasswordException();
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getRepeatPassword())) {
            throw AccountException.repeatPasswordDiffersFromNewPasswordException();
        }
        if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getOldPassword())) {
            throw AccountException.changeToTheSamePasswordException();
        }
        account.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));

        accountRepository.update(account);
    }

    private Account prepareAccountToRegister(SignUpRequest signUpRequest) {
        Address address = Address.builder()
                .city(signUpRequest.getCity())
                .street(signUpRequest.getStreet())
                .houseNumber(signUpRequest.getHouseNumber())
                .build();
        PersonalData personalData = PersonalData.builder()
                .address(address)
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .yearsOld(signUpRequest.getYearsOld())
                .build();

        return Account.builder()
                .login(signUpRequest.getLogin())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .active(true)
                .roles(new ArrayList<>())
                .personalData(personalData)
                .build();
    }

}
