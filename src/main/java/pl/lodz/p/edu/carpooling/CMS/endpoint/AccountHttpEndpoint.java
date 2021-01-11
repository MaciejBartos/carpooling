package pl.lodz.p.edu.carpooling.CMS.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDetailsDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountRolesDTO;
import pl.lodz.p.edu.carpooling.CMS.request.ChangePasswordRequest;
import pl.lodz.p.edu.carpooling.CMS.request.GetAccountDetailsByLoginRequest;
import pl.lodz.p.edu.carpooling.CMS.request.GetAccountsSearchCriteriaRequest;
import pl.lodz.p.edu.carpooling.CMS.request.UpdateAccountRequest;
import pl.lodz.p.edu.carpooling.CMS.response.AccountDetailsResponse;
import pl.lodz.p.edu.carpooling.CMS.response.GetAccountsResponse;
import pl.lodz.p.edu.carpooling.CMS.service.AccountService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class AccountHttpEndpoint {

    private final AccountService accountService;

    @GetMapping(path = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDetailsDTO getAccountDetailsById(@PathVariable Long id) {
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

    @PostMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public GetAccountsResponse getAccounts(@RequestBody GetAccountsSearchCriteriaRequest searchCriteriaRequest) {
        return GetAccountsResponse.builder()
                .accounts(accountService.getAccounts(searchCriteriaRequest.getSearchCriteria()))
                .build();
    }
}
