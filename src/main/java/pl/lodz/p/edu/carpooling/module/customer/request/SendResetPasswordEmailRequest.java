package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class SendResetPasswordEmailRequest {

    @NotEmpty
    @Email
    private String email;
}
