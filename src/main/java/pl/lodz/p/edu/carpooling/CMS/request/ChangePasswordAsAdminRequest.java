package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class ChangePasswordAsAdminRequest {
    private String accountId;
    private String password;
    private String repeatPassword;
}
