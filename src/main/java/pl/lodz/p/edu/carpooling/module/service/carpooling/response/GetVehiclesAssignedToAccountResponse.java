package pl.lodz.p.edu.carpooling.module.service.carpooling.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.module.service.carpooling.response.model.VehicleDetailsForList;

import java.util.List;

@Data
@Builder
public class GetVehiclesAssignedToAccountResponse {

    private List<VehicleDetailsForList> vehicles;
}
