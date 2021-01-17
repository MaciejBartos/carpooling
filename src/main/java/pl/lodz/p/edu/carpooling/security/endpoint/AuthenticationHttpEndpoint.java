package pl.lodz.p.edu.carpooling.security.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.CMS.service.AccountService;
import pl.lodz.p.edu.carpooling.security.request.ConfirmAccountRequest;
import pl.lodz.p.edu.carpooling.security.request.SignInRequest;
import pl.lodz.p.edu.carpooling.security.request.SignUpRequest;
import pl.lodz.p.edu.carpooling.security.response.JwtResponse;
import pl.lodz.p.edu.carpooling.security.util.JwtUtils;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationHttpEndpoint {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    @ResponseStatus(value = HttpStatus.OK)
    public JwtResponse authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getLogin(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.createJwtResponse(authentication);
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        accountService.registerAccount(signUpRequest);
    }

    @PutMapping(path = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void confirmAccount(@Valid @RequestBody ConfirmAccountRequest confirmAccountRequest) {
        accountService.confirmAccount(confirmAccountRequest);
    }

}
