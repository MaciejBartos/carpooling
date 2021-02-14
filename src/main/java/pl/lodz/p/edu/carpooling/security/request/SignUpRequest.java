package pl.lodz.p.edu.carpooling.security.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class SignUpRequest {

    @Size(min = 6, max = 64)
    @NotEmpty
    private String login;
    @Email
    @NotEmpty
    private String email;
    @Size(min = 8, max = 64)
    @NotEmpty
    private String password;
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
    @Size(max = 64)
    @Pattern(regexp = "^[A-Za-zążźęćół ]+$")
    @NotEmpty
    private String city;
    @Size(max = 64)
    @Pattern(regexp = "^[A-Za-zążźęćół ]+$")
    @NotEmpty
    private String street;
    @Size(max = 10)
    @Pattern(regexp = "^[1-9][0-9]*")
    @NotEmpty
    private Long houseNumber;
}
