package pl.lodz.p.edu.carpooling.module.service.carpooling.request;

import lombok.Data;

@Data
public class GetVehicleAssignedToAccountRequest {

    private String accountLogin;
}
