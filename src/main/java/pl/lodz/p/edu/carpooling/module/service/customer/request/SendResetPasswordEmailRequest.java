package pl.lodz.p.edu.carpooling.module.service.customer.request;

import lombok.Data;

@Data
public class SendResetPasswordEmailRequest {
    private String email;
}
