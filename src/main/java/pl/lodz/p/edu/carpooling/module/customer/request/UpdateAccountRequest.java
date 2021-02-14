package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UpdateAccountRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private String id;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private Long version;
}
