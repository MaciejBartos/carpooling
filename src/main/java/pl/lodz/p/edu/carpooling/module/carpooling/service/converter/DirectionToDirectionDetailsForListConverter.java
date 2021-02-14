package pl.lodz.p.edu.carpooling.module.carpooling.service.converter;

import pl.lodz.p.edu.carpooling.module.carpooling.response.model.DirectionDetailsForList;
import pl.lodz.p.edu.carpooling.persistence.entity.Direction;

public class DirectionToDirectionDetailsForListConverter {

    public static DirectionDetailsForList convert(Direction direction) {
        return DirectionDetailsForList.builder()
                .directionId(String.valueOf(direction.getId()))
                .originAddress(direction.getOriginAddress())
                .destinationAddress(direction.getDestinationAddress())
                .driverName(direction.getHost().getPersonalData().getName())
                .driverSurName(direction.getHost().getPersonalData().getSurname())
                .travelDate(direction.getTravelDate())
                .deleteAction(direction.isActive())
                .build();
    }
}
