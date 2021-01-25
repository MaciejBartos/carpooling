package pl.lodz.p.edu.carpooling.CMS.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountRolesDTO;
import pl.lodz.p.edu.carpooling.CMS.request.*;
import pl.lodz.p.edu.carpooling.CMS.response.AccountDetailsResponse;
import pl.lodz.p.edu.carpooling.CMS.response.model.AccountDetailsForList;
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
import pl.lodz.p.edu.carpooling.security.request.ConfirmAccountRequest;
import pl.lodz.p.edu.carpooling.security.request.SignUpRequest;
import pl.lodz.p.edu.carpooling.util.ConfirmationTokenUtil;
import pl.lodz.p.edu.carpooling.util.email.EmailService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class AccountService {

    private final ConfirmationTokenUtil confirmationTokenUtil;
    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    public void registerAccount(SignUpRequest signUpRequest) {
        Role userRole = roleRepository.findRoleByName(RoleEnum.ROLE_USER);
        Account account = prepareAccountToRegister(signUpRequest);
        account.setActive(true);
        account.setPassword(encoder.encode(signUpRequest.getPassword()));
        account.getRoles().add(userRole);
        account.setExpiryDateOfEmailToken(confirmationTokenUtil.getTokenExpireDate());
        account.setConfirmationEmailToken(confirmationTokenUtil.generateEmailToken(account.getLogin(),
                account.getExpiryDateOfEmailToken()));
        accountRepository.save(account);
        emailService.sendConfirmationRegistrationEmail(account.getConfirmationEmailToken(), account.getEmail());
    }

    public void confirmAccount(ConfirmAccountRequest confirmAccountRequest) {
        Account account = accountRepository.findByConfirmationToken(confirmAccountRequest.getToken());
        if (account.getExpiryDateOfEmailToken().isBefore(LocalDateTime.now())) {
            throw AccountException.emailTokenExpiredException();
        }
        if (account.isConfirmed()) {
            throw AccountException.accountAlreadyConfirmedException();
        }
        account.setConfirmed(true);
        accountRepository.save(account);
    }

    public void changeAccountStatus(ChangeAccountStatusRequest changeAccountStatusRequest) {
        Account account = accountRepository.findById(Long.parseLong(changeAccountStatusRequest.getId()));
        account.setActive(!account.isActive());
        accountRepository.update(account);
    }

    public AccountDetailsResponse getAccountById(Long id) {
        return AccountToAccountDetailsResponseConverter.convert(accountRepository.findById(id));
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
        Account account = accountRepository.findById(Long.parseLong(accountDto.getId()));
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

    public void updateAccountPasswordAsAdmin(ChangePasswordAsAdminRequest changePasswordAsAdminRequest) {
        Account account = accountRepository.findById(Long.valueOf(changePasswordAsAdminRequest.getAccountId()));
        if (!changePasswordAsAdminRequest.getPassword().equals(changePasswordAsAdminRequest.getRepeatPassword())) {
            throw AccountException.repeatPasswordDiffersFromNewPasswordException();
        }
        if (encoder.matches(changePasswordAsAdminRequest.getPassword(), account.getPassword())) {
            throw AccountException.changeToTheSamePasswordException();
        }
        account.setPassword(encoder.encode(changePasswordAsAdminRequest.getPassword()));
        accountRepository.update(account);
    }

    public void sendResetPasswordEmail(SendResetPasswordEmailRequest sendResetPasswordEmailRequest) {
        Account account = accountRepository.findByEmail(sendResetPasswordEmailRequest.getEmail());
        if (!account.isConfirmed()) {
            throw AccountException.accountNotConfirmedException();
        }
        if (!account.isActive()) {
            throw AccountException.accountNotActiveException();
        }
        account.setExpiryDateOfEmailToken(confirmationTokenUtil.getTokenExpireDate());
        account.setResetPasswordEmailToken(confirmationTokenUtil.generateEmailToken(account.getLogin(),
                account.getExpiryDateOfEmailToken()));
        accountRepository.save(account);
        emailService.sendResetPasswordEmail(account.getResetPasswordEmailToken(), account.getEmail());
    }

    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Account account = accountRepository.findByResetPasswordToken(resetPasswordRequest.getToken());
        if (!resetPasswordRequest.getPassword().equals(resetPasswordRequest.getRepeatPassword())) {
            throw AccountException.repeatPasswordDiffersFromNewPasswordException();
        }
        if (encoder.matches(resetPasswordRequest.getPassword(), account.getPassword())) {
            throw AccountException.changeToTheSamePasswordException();
        }
        account.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
        account.setResetPasswordEmailToken(null);
        accountRepository.save(account);
    }

    public void verifyResetPasswordToken(VerifyResetPasswordTokenRequest verifyResetPasswordTokenRequest) {
        accountRepository.findByResetPasswordToken(verifyResetPasswordTokenRequest.getToken());
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
                .birthDate(signUpRequest.getBirthDate())
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
