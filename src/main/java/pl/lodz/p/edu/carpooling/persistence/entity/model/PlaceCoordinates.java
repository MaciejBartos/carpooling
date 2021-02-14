package pl.lodz.p.edu.carpooling.persistence.entity.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class PlaceCoordinates implements Serializable {

    private double longitude;
    private double latitude;
}
