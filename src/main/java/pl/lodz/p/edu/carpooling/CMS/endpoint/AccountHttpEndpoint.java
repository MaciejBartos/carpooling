package pl.lodz.p.edu.carpooling.CMS.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDetailsDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.ChangePasswordDTO;
import pl.lodz.p.edu.carpooling.CMS.service.AccountService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class AccountHttpEndpoint {

    private final AccountService accountService;

//    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public AccountDTO getAccountById(@PathVariable Long id) {
//        Account account = accountService.getAccountById(id);
//        return AccountDTO.builder()
//                .build();
//    }

    @GetMapping(path = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDetailsDTO getAccountDetailsById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping(path = "/update")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateAccount(@Valid @RequestBody AccountDTO accountDto) {
        accountService.updateAccount(accountDto);
    }

    @PutMapping(path = "/password")
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        accountService.updateAccountPassword(changePasswordDTO);
    }
}
