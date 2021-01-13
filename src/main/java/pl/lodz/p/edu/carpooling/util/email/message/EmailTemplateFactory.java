package pl.lodz.p.edu.carpooling.util.email.message;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplateFactory {

    public EmailTemplate createRegistrationEmailTemplate(String token) {
        return new RegistrationEmailTemplate(token);
    }

}
