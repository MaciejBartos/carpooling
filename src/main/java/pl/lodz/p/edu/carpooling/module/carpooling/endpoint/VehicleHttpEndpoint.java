package pl.lodz.p.edu.carpooling.module.carpooling.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.carpooling.request.CreateVehicleRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.request.UpdateVehicleRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.response.GetVehiclesAssignedToAccountResponse;
import pl.lodz.p.edu.carpooling.module.carpooling.response.GetVehicleDetailsToUpdateResponse;
import pl.lodz.p.edu.carpooling.module.carpooling.service.VehicleService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/vehicle")
@RequiredArgsConstructor
public class VehicleHttpEndpoint {

    private final VehicleService vehicleService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @RolesAllowed("ROLE_USER")
    public void createVehicle(@Valid @RequestBody CreateVehicleRequest request) {
        vehicleService.createVehicle(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public GetVehiclesAssignedToAccountResponse getVehiclesAssignedToAccount() {
        return GetVehiclesAssignedToAccountResponse.builder()
                .vehicles(vehicleService.getVehiclesAssignedToAuthenticatedAccount())
                .build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public GetVehicleDetailsToUpdateResponse getVehicleDetails(@PathVariable Long id) {
        return vehicleService.getVehicleDetails(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void updateVehicle(@Valid @RequestBody UpdateVehicleRequest request) {
        vehicleService.updateVehicle(request);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
    }
}
