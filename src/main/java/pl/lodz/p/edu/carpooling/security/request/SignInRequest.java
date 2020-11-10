package pl.lodz.p.edu.carpooling.security.request;

import lombok.Data;

@Data
public class SignInRequest {

    private String login;
    private String password;
}
