package pl.lodz.p.edu.carpooling.module.service.customer.request;

import lombok.Data;

@Data
public class ChangePasswordAsAdminRequest {
    private String accountId;
    private String password;
    private String repeatPassword;
}
