package pl.lodz.p.edu.carpooling.module.carpooling.request;

import lombok.Data;
import pl.lodz.p.edu.carpooling.module.carpooling.request.model.LatitudeLongitude;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateDirectionRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private String vehicleId;
    @NotNull
    private LatitudeLongitude origin;
    @NotEmpty
    private String originAddress;
    @NotNull
    private LatitudeLongitude destination;
    @NotEmpty
    private String destinationAddress;
    @NotEmpty
    private List<LatitudeLongitude> waypoints;
    @Future
    @NotNull
    private LocalDateTime travelDate;
    @NotNull
    private int numberOfFreeSeats;
}
