package pl.lodz.p.edu.carpooling.module.carpooling.request;

import lombok.Data;
import pl.lodz.p.edu.carpooling.module.carpooling.request.model.LatitudeLongitude;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class GetDirectionForGivenCoordinatesRequest {

    @NotNull
    private LatitudeLongitude origin;
    @NotNull
    private LatitudeLongitude destination;
    @NotNull
    private long distance;
    @Future
    @NotNull
    private LocalDate travelDate;
}
