package pl.lodz.p.edu.carpooling.security.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.CMS.services.AccountService;
import pl.lodz.p.edu.carpooling.persistance.entities.Account;
import pl.lodz.p.edu.carpooling.persistance.entities.Address;
import pl.lodz.p.edu.carpooling.persistance.entities.PersonalData;
import pl.lodz.p.edu.carpooling.security.requests.SignInRequest;
import pl.lodz.p.edu.carpooling.security.requests.SignUpRequest;
import pl.lodz.p.edu.carpooling.security.responses.JwtResponse;
import pl.lodz.p.edu.carpooling.security.utils.JwtUtils;

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
        Address address = Address.builder()
                .city(signUpRequest.getCity())
                .street(signUpRequest.getStreet())
                .houseNumber(signUpRequest.getHouseNumber())
                .build();
        PersonalData personalData = PersonalData.builder()
                .address(address)
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .yearsOld(signUpRequest.getYearsOld())
                .build();
        Account account = Account.builder()
                .email(signUpRequest.getEmail())
                .login(signUpRequest.getLogin())
                .password(signUpRequest.getPassword())
                .personalData(personalData)
                .build();
        accountService.registerAccount(account);
    }
}
