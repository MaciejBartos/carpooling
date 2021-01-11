package pl.lodz.p.edu.carpooling.CMS.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.CMS.request.UpdatePersonalDataRequest;
import pl.lodz.p.edu.carpooling.CMS.service.PersonalDataService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/personal-data")
public class PersonalDataHttpEndpoint {

    private final PersonalDataService personalDataService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UpdatePersonalDataRequest personalData) {
        personalDataService.update(personalData);
    }
}
