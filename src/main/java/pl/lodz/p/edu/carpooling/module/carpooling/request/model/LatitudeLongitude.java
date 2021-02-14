package pl.lodz.p.edu.carpooling.module.carpooling.request.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LatitudeLongitude {

    private double latitude;
    private double longitude;
}
