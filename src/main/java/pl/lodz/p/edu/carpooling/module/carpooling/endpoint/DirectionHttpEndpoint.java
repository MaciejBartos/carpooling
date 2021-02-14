package pl.lodz.p.edu.carpooling.module.carpooling.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.carpooling.response.CreateDirectionResponse;
import pl.lodz.p.edu.carpooling.module.carpooling.response.GetDirectionDetailsResponse;
import pl.lodz.p.edu.carpooling.module.carpooling.response.GetDirectionsResponse;
import pl.lodz.p.edu.carpooling.module.carpooling.request.AssignToDirectionRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.request.CreateDirectionRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.request.GetDirectionForGivenCoordinatesRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.request.ResignFromDirectionRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.service.DirectionService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/direction")
@RequiredArgsConstructor
public class DirectionHttpEndpoint {

    private final DirectionService directionService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public CreateDirectionResponse createDirection(@Valid @RequestBody CreateDirectionRequest request) {
        return directionService.createDirection(request);
    }

    @PostMapping(path = "/filter")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public GetDirectionsResponse getDirections(@Valid @RequestBody GetDirectionForGivenCoordinatesRequest request) {
        return GetDirectionsResponse.builder()
                .directions(directionService.getDirections(request))
                .build();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public GetDirectionDetailsResponse getDirectionDetails(@PathVariable("id") String id) {
        return directionService.getDirectionDetails(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void deleteDirection(@PathVariable("id") String id) {
        directionService.deleteDirection(id);
    }

    @PutMapping(path = "/assign")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void assignToDirection(@Valid @RequestBody AssignToDirectionRequest request) {
        directionService.assignToDirection(request);
    }

    @GetMapping(path = "/assign")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public GetDirectionsResponse getDirectionsAssignedToCurrentUser() {
        return GetDirectionsResponse.builder()
                .directions(directionService.getDirectionsAssignedToCurrentUser())
                .build();
    }

    @PutMapping(path = "/resign")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public void resignFromDirection(@Valid @RequestBody ResignFromDirectionRequest request) {
        directionService.resignFromDirection(request);
    }

    @GetMapping(path = "/created")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_USER")
    public GetDirectionsResponse getDirectionsCreatedByCurrentUser() {
        return GetDirectionsResponse.builder()
                .directions(directionService.getDirectionsCreatedByCurrentUser())
                .build();
    }
}
