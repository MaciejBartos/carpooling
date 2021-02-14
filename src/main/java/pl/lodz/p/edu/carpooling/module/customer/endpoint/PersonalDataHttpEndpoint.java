package pl.lodz.p.edu.carpooling.module.customer.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.customer.request.UpdatePersonalDataRequest;
import pl.lodz.p.edu.carpooling.module.customer.service.PersonalDataService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/personal-data")
public class PersonalDataHttpEndpoint {

    private final PersonalDataService personalDataService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void update(@Valid @RequestBody UpdatePersonalDataRequest personalData) {
        personalDataService.update(personalData);
    }
}
