package pl.lodz.p.edu.carpooling.module.service.carpooling.response.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDetailsForList {

    private String id;
    private String brand;
    private String model;
}
