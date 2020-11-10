package pl.lodz.p.edu.carpooling.security.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {

    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Long yearsOld;
    private String city;
    private String street;
    private Long houseNumber;
}
