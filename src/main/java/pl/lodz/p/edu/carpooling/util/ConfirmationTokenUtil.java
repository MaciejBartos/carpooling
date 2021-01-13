package pl.lodz.p.edu.carpooling.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ConfirmationTokenUtil {

    private final PasswordEncoder encoder;
    @Value("${confirm.account.key}")
    private String secretKey;
    @Value("${confirm.account.timeToConfirmInMinutes}")
    private String timeToConfirmAccount;

    public LocalDateTime getTokenExpireDate() {
        return LocalDateTime.now().plusMinutes(Long.parseLong(timeToConfirmAccount));
    }

    public String generateConfirmationToken(String value, LocalDateTime dateTime) {
        return encoder.encode(value + secretKey + dateTime);
    }
}
