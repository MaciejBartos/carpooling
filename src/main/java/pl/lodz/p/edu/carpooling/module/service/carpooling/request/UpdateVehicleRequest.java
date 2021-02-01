package pl.lodz.p.edu.carpooling.module.service.carpooling.request;

import lombok.Data;

@Data
public class UpdateVehicleRequest {

    private String id;
    private String brand;
    private String model;
    private long productionYear;
    private String description;
    private long numberOfSeats;
    private long version;
}
