package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UpdatePersonalDataRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private String id;
    @Size(max = 64)
    @Pattern(regexp = "^[A-Za-zążźęćół]+$")
    @NotEmpty
    private String name;
    @Size(max = 64)
    @Pattern(regexp = "^[A-Za-zążźęćół]+$")
    @NotEmpty
    private String surname;
    @Past
    @NotEmpty
    private LocalDate birthDate;
    @NotEmpty
    private Long version;
}
