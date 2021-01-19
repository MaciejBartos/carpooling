package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class VerifyResetPasswordTokenRequest {
    private String token;
}
