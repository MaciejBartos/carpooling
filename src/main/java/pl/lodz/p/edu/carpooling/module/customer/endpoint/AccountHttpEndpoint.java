package pl.lodz.p.edu.carpooling.module.customer.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.customer.request.*;
import pl.lodz.p.edu.carpooling.module.customer.response.AccountDetailsResponse;
import pl.lodz.p.edu.carpooling.module.customer.response.GetAccountsResponse;
import pl.lodz.p.edu.carpooling.module.customer.service.AccountService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class AccountHttpEndpoint {

    private final AccountService accountService;

    @GetMapping(path = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public AccountDetailsResponse getAccountDetailsById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void updateAccount(@Valid @RequestBody UpdateAccountRequest account) {
        accountService.updateAccount(account);
    }

    @PutMapping(path = "/password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void updatePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        accountService.updateAccountPassword(changePasswordRequest);
    }

    @PostMapping(path = "/password/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public void sendResetPasswordEmail(@Valid @RequestBody SendResetPasswordEmailRequest sendResetPasswordEmailRequest) {
        accountService.sendResetPasswordEmail(sendResetPasswordEmailRequest);
    }

    @PutMapping(path = "/password/reset", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        accountService.resetPassword(resetPasswordRequest);
    }

    @PostMapping(path = "/password/reset/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public void verifyResetPasswordToken(@Valid @RequestBody VerifyResetPasswordTokenRequest verifyResetPasswordTokenRequest) {
        accountService.verifyResetPasswordToken(verifyResetPasswordTokenRequest);
    }

    @PostMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_ADMIN")
    public GetAccountsResponse getAccounts(@Valid @RequestBody GetAccountsSearchCriteriaRequest searchCriteriaRequest) {
        return GetAccountsResponse.builder()
                .accounts(accountService.getAccounts(searchCriteriaRequest))
                .build();
    }

    @PutMapping(path="/admin/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_ADMIN")
    public void changeAccountStatus(@Valid @RequestBody ChangeAccountStatusRequest changeAccountStatusRequest) {
        accountService.changeAccountStatus(changeAccountStatusRequest);
    }

    @PutMapping(path="/admin/password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_ADMIN")
    public void updatePasswordAsAdmin(@Valid @RequestBody ChangePasswordAsAdminRequest changeAccountPasswordRequest) {
        accountService.updateAccountPasswordAsAdmin(changeAccountPasswordRequest);
    }
}
