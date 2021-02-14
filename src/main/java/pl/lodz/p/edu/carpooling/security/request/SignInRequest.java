package pl.lodz.p.edu.carpooling.security.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignInRequest {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
