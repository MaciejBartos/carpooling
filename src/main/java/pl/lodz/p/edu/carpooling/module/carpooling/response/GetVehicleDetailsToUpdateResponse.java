package pl.lodz.p.edu.carpooling.module.carpooling.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetVehicleDetailsToUpdateResponse {

    private String id;
    private String brand;
    private String model;
    private long productionYear;
    private String description;
    private long numberOfSeats;
    private long version;
}
