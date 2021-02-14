package pl.lodz.p.edu.carpooling.module.carpooling.service.converter;

import pl.lodz.p.edu.carpooling.module.carpooling.response.model.VehicleDetailsForList;
import pl.lodz.p.edu.carpooling.persistence.entity.Vehicle;

public class VehicleToVehicleDetailsForListConverter {

    public static VehicleDetailsForList convert(Vehicle vehicle) {
        return VehicleDetailsForList.builder()
                .id(vehicle.getId().toString())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .numberOfSeats(vehicle.getNumberOfSeats())
                .build();
    }
}
