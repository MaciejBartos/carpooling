package pl.lodz.p.edu.carpooling.module.service.carpooling.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateVehicleRequest {

    private String brand;
    private String model;
    private LocalDate productionDate;
    private String description;
    private long numberOfSeats;
}
