package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ChangeAccountStatusRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private String id;
}
