package pl.lodz.p.edu.carpooling.security.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SignUpRequest {

    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String city;
    private String street;
    private Long houseNumber;
}
