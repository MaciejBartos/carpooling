package pl.lodz.p.edu.carpooling.CMS.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.CMS.request.UpdateAddressRequest;
import pl.lodz.p.edu.carpooling.CMS.service.AddressService;

@RestController
@RequestMapping(path = "/address")
@RequiredArgsConstructor
public class AddressHttpEndpoint {

    private final AddressService addressService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UpdateAddressRequest address) {
        addressService.update(address);
    }
}
