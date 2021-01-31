package pl.lodz.p.edu.carpooling.module.service.customer.request;

import lombok.Data;

@Data
public class UpdateAccountRequest {

    private String id;
    private String email;
    private Long version;
}
