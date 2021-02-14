package pl.lodz.p.edu.carpooling.module.carpooling.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.module.carpooling.response.model.VehicleDetailsForList;

import java.util.List;

@Data
@Builder
public class GetVehiclesAssignedToAccountResponse {

    private List<VehicleDetailsForList> vehicles;
}
