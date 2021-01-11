package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String accountId;
    private String oldPassword;
    private String newPassword;
    private String repeatPassword;
}
