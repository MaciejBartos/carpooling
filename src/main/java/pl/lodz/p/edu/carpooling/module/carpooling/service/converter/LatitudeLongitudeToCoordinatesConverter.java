package pl.lodz.p.edu.carpooling.module.carpooling.service.converter;

import pl.lodz.p.edu.carpooling.module.carpooling.request.model.LatitudeLongitude;
import pl.lodz.p.edu.carpooling.persistence.entity.Coordinate;

public class LatitudeLongitudeToCoordinatesConverter {

    public static Coordinate convert(LatitudeLongitude o) {
        return Coordinate.builder()
                .latitude(o.getLatitude())
                .longitude(o.getLongitude())
                .build();
    }
}
