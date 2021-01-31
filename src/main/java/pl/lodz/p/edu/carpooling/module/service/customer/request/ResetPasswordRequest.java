package pl.lodz.p.edu.carpooling.module.service.customer.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String password;
    private String repeatPassword;
}
