package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VerifyResetPasswordTokenRequest {

    @NotEmpty
    private String token;
}
