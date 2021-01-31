package pl.lodz.p.edu.carpooling.module.service.customer.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String accountId;
    private String oldPassword;
    private String newPassword;
    private String repeatPassword;
}
