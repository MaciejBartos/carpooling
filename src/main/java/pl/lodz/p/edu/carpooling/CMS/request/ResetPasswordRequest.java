package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String password;
    private String repeatPassword;
}
