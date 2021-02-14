package pl.lodz.p.edu.carpooling.module.customer.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private Long accountId;
    private String password;
    private Long version;
}
