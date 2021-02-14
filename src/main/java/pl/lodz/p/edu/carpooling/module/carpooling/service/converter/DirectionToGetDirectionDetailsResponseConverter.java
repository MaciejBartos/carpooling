package pl.lodz.p.edu.carpooling.module.carpooling.service.converter;

import pl.lodz.p.edu.carpooling.module.carpooling.response.GetDirectionDetailsResponse;
import pl.lodz.p.edu.carpooling.persistence.entity.Direction;

import java.util.stream.Collectors;

public class DirectionToGetDirectionDetailsResponseConverter {

    public static GetDirectionDetailsResponse convert(Direction direction) {
        return GetDirectionDetailsResponse.builder()
                .id(direction.getId().toString())
                .vehicleBrand(direction.getVehicle().getBrand())
                .vehicleModel(direction.getVehicle().getModel())
                .vehicleProductionYear(String.valueOf(direction.getVehicle().getProductionYear()))
                .driverName(direction.getHost().getPersonalData().getName())
                .driverSurname(direction.getHost().getPersonalData().getSurname())
                .destination(CoordinatesToLatitudeLongitudeConverter.convert(direction.getDestination()))
                .destinationAddress(direction.getDestinationAddress())
                .origin(CoordinatesToLatitudeLongitudeConverter.convert(direction.getOrigin()))
                .originAddress(direction.getOriginAddress())
                .steps(direction.getWaypoint().stream()
                            .map(CoordinatesToLatitudeLongitudeConverter::convert)
                            .collect(Collectors.toList()))
                .travelDate(direction.getTravelDate())
                .build();
    }
}
