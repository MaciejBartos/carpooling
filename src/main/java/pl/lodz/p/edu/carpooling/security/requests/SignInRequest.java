package pl.lodz.p.edu.carpooling.security.requests;

import lombok.Data;

@Data
public class SignInRequest {

    private String login;
    private String password;
}
