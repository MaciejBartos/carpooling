package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UpdateUserRolesRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private final String accountId;
    @NotNull
    private final List<String> roles;
}
