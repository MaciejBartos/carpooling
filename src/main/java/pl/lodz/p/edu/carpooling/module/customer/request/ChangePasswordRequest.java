package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private String accountId;
    @Size(min = 8, max = 64)
    @NotEmpty
    private String oldPassword;
    @Size(min = 8, max = 64)
    @NotEmpty
    private String newPassword;
    @Size(min = 8, max = 64)
    @NotEmpty
    private String repeatPassword;
}
