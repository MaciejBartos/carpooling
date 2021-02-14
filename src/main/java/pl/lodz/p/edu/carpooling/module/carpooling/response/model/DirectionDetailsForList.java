package pl.lodz.p.edu.carpooling.module.carpooling.response.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DirectionDetailsForList {

    private String directionId;
    private String originAddress;
    private String destinationAddress;
    private String driverName;
    private String driverSurName;
    private LocalDateTime travelDate;
    private boolean deleteAction;

}
