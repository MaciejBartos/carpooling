package pl.lodz.p.edu.carpooling.module.carpooling.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.module.carpooling.request.model.LatitudeLongitude;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetDirectionDetailsResponse {

    private String id;
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleProductionYear;
    private String driverName;
    private String driverSurname;
    private LatitudeLongitude origin;
    private String originAddress;
    private LatitudeLongitude destination;
    private String destinationAddress;
    private List<LatitudeLongitude> steps;
    private LocalDateTime travelDate;
    private int numberOfAvailableSeats;
    private boolean assignAction;
    private boolean resignAction;
    private boolean currentAccountOwner;

}
