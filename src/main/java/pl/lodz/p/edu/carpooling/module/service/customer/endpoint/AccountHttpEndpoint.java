package pl.lodz.p.edu.carpooling.module.service.customer.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.service.customer.dto.AccountRolesDTO;
import pl.lodz.p.edu.carpooling.module.service.customer.request.*;
import pl.lodz.p.edu.carpooling.module.service.customer.response.AccountDetailsResponse;
import pl.lodz.p.edu.carpooling.module.service.customer.response.GetAccountsResponse;
import pl.lodz.p.edu.carpooling.module.service.customer.service.AccountService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class AccountHttpEndpoint {

    private final AccountService accountService;

    @GetMapping(path = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDetailsResponse getAccountDetailsById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDetailsResponse getAccountDetailsByLogin(@RequestBody GetAccountDetailsByLoginRequest accountDetailsByLogin) {
        return accountService.getAccountDetailsByLogin(accountDetailsByLogin.getLogin());
    }

    @PutMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateAccount(@Valid @RequestBody UpdateAccountRequest account) {
        accountService.updateAccount(account);
    }

    @PutMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateRoles(@Valid @RequestBody AccountRolesDTO accountRolesDTO) {
        accountService.updateRoles(accountRolesDTO);
    }

    @PutMapping(path = "/password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        accountService.updateAccountPassword(changePasswordRequest);
    }

    @PostMapping(path = "/password/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void sendResetPasswordEmail(@RequestBody SendResetPasswordEmailRequest sendResetPasswordEmailRequest) {
        accountService.sendResetPasswordEmail(sendResetPasswordEmailRequest);
    }

    @PutMapping(path = "/password/reset", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        accountService.resetPassword(resetPasswordRequest);
    }

    @PostMapping(path = "/password/reset/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void verifyResetPasswordToken(@RequestBody VerifyResetPasswordTokenRequest verifyResetPasswordTokenRequest) {
        accountService.verifyResetPasswordToken(verifyResetPasswordTokenRequest);
    }

    @PostMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public GetAccountsResponse getAccounts(@RequestBody GetAccountsSearchCriteriaRequest searchCriteriaRequest) {
        return GetAccountsResponse.builder()
                .accounts(accountService.getAccounts(searchCriteriaRequest))
                .build();
    }

    @PutMapping(path="/admin/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeAccountStatus(@RequestBody ChangeAccountStatusRequest changeAccountStatusRequest) {
        accountService.changeAccountStatus(changeAccountStatusRequest);
    }

    @PutMapping(path="/admin/password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePasswordAsAdmin(@RequestBody ChangePasswordAsAdminRequest changeAccountPasswordRequest) {
        accountService.updateAccountPasswordAsAdmin(changeAccountPasswordRequest);
    }
}
