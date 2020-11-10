package pl.lodz.p.edu.carpooling.CMS.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.CMS.dtos.AccountDTO;
import pl.lodz.p.edu.carpooling.CMS.services.AccountService;
import pl.lodz.p.edu.carpooling.persistance.entities.Account;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class AccountHttpEndpoint {

    private final AccountService accountService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDTO getAccountById(@PathVariable() Long id) {
        Account account = accountService.getAccountById(id);
        return AccountDTO.builder()
                .build();
    }
}
