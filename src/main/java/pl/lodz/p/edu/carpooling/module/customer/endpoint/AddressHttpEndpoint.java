package pl.lodz.p.edu.carpooling.module.customer.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.customer.service.AddressService;
import pl.lodz.p.edu.carpooling.module.customer.request.UpdateAddressRequest;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/address")
@RequiredArgsConstructor
public class AddressHttpEndpoint {

    private final AddressService addressService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void update(@Valid @RequestBody UpdateAddressRequest address) {
        addressService.update(address);
    }
}
