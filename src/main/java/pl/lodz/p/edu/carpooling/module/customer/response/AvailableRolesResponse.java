package pl.lodz.p.edu.carpooling.module.customer.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AvailableRolesResponse {

    private final List<String> roles;
}
