package pl.lodz.p.edu.carpooling.module.service.customer.request;

import lombok.Data;

@Data
public class VerifyResetPasswordTokenRequest {
    private String token;
}
