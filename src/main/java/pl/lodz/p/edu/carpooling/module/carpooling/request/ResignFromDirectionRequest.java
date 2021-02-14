package pl.lodz.p.edu.carpooling.module.carpooling.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ResignFromDirectionRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private String directionId;
}
