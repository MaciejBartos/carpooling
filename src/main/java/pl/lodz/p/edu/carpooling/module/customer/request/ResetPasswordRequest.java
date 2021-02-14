package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordRequest {

    @NotEmpty
    private String token;
    @Size(min = 8, max = 64)
    @NotEmpty
    private String password;
    @Size(min = 8, max = 64)
    @NotEmpty
    private String repeatPassword;
}
