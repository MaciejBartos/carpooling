package pl.lodz.p.edu.carpooling.CMS.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDetailsDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.ChangePasswordDTO;
import pl.lodz.p.edu.carpooling.CMS.service.converter.AccountToAccountDetailsDTOConverter;
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
        account.setPassword(encoder.encode(account.getPassword()));
        account.getRoles().add(userRole);
        accountRepository.save(account);
    }

    public AccountDetailsDTO getAccountById(Long id) {
        return AccountToAccountDetailsDTOConverter.entityToDto(accountRepository.findById(id));
    }

    public void updateAccount(AccountDTO accountDto) {
        Account account = accountRepository.findById(accountDto.getId());
        account.setEmail(accountDto.getEmail());
        accountRepository.update(account);
    }

    public void updateAccountPassword(ChangePasswordDTO changePasswordDTO) {
        Account account = accountRepository.findById(changePasswordDTO.getAccountId());
        if (encoder.matches(changePasswordDTO.getPassword(), account.getPassword())) {
            throw AccountException.changeToTheSamePasswordException();
        }
        account.setPassword(encoder.encode(changePasswordDTO.getPassword()));
        account.setVersion(changePasswordDTO.getVersion());

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
                .personalData(personalData)
                .build();
    }

}
