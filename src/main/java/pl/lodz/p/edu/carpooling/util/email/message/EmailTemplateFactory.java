package pl.lodz.p.edu.carpooling.util.email.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplateFactory {

    @Value("${application.deploy.address}")
    private String address;

    public EmailTemplate createRegistrationEmailTemplate(String token) {
        return new RegistrationEmailTemplate(token, address);
    }

    public EmailTemplate createResetPasswordEmailTemplate(String token) {
        return new ResetPasswordEmailTemplate(token, address);
    }

    public EmailTemplate createDirectionDeletedEmailTemplate(String directionId) {
        return new DeletedDirectionEmailTemplate(directionId, address);
    }

}
