package pl.lodz.p.edu.carpooling.module.customer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountRolesDTO {

    private Long id;
    private List<String> roles;
    private Long version;
}
