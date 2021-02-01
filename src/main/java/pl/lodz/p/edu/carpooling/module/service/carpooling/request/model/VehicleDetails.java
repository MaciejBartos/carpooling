package pl.lodz.p.edu.carpooling.module.service.carpooling.request.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VehicleDetails {

    private String id;
    private String brand;
    private String model;
    private LocalDate productionDate;
    private String description;
    private long numberOfSeats;
    private long version;
}
