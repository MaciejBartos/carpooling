package pl.lodz.p.edu.carpooling.module.carpooling.service.converter;

import pl.lodz.p.edu.carpooling.module.carpooling.request.model.LatitudeLongitude;
import pl.lodz.p.edu.carpooling.persistence.entity.Coordinate;

public class CoordinatesToLatitudeLongitudeConverter {

    public static LatitudeLongitude convert(Coordinate p) {
        return LatitudeLongitude.builder()
                .latitude(p.getLatitude())
                .longitude(p.getLongitude())
                .build();
    }

}
