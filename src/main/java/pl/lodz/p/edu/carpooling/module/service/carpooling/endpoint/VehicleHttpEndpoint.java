package pl.lodz.p.edu.carpooling.module.service.carpooling.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.service.carpooling.request.CreateVehicleRequest;
import pl.lodz.p.edu.carpooling.module.service.carpooling.request.UpdateVehicleRequest;
import pl.lodz.p.edu.carpooling.module.service.carpooling.response.GetVehicleDetailsToUpdateResponse;
import pl.lodz.p.edu.carpooling.module.service.carpooling.response.GetVehiclesAssignedToAccountResponse;
import pl.lodz.p.edu.carpooling.module.service.carpooling.service.VehicleService;

@RestController
@RequestMapping(path = "/vehicle")
@RequiredArgsConstructor
public class VehicleHttpEndpoint {

    private final VehicleService vehicleService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createVehicle(@RequestBody CreateVehicleRequest request) {
        vehicleService.createVehicle(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public GetVehiclesAssignedToAccountResponse getVehiclesAssignedToAccount() {
        return GetVehiclesAssignedToAccountResponse.builder()
                .vehicles(vehicleService.getVehiclesAssignedToAuthenticatedAccount())
                .build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public GetVehicleDetailsToUpdateResponse getVehicleDetails(@PathVariable Long id) {
        return vehicleService.getVehicleDetails(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateVehicle(@RequestBody UpdateVehicleRequest request) {
        vehicleService.updateVehicle(request);
    }
}
