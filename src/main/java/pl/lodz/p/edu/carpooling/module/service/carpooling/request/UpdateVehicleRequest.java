package pl.lodz.p.edu.carpooling.module.service.carpooling.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVehicleRequest {

    private long id;
    private String model;
    private String brand;
    private LocalDate productionDate;
    private long numberOfSeats;
    private String description;
    private long version;
}
