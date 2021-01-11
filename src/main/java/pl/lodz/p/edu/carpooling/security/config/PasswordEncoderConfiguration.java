package pl.lodz.p.edu.carpooling.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        int strength = 10;
        SecureRandom random = new SecureRandom("secret".getBytes());
        return new BCryptPasswordEncoder(strength, random);
    }
}
