package pl.lodz.p.edu.carpooling.security.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class JwtResponse {

    private String token;
    private final String type = "Bearer";
    private Long id;
    private String login;
    private String email;
    private List<String> roles;
}
