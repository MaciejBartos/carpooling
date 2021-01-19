package pl.lodz.p.edu.carpooling.util.email.message;

import lombok.Getter;

@Getter
public class RegistrationEmailTemplate implements EmailTemplate {

    private final String subject;
    private final String body;

    RegistrationEmailTemplate(String confirmAccountToken, String address) {
        String link = address + "/register/confirm?token=" + confirmAccountToken;
        subject = "Confirm your account!";
        body = "To finalize registration process you have to confirm your account by clicking in the link below <br/>" +
                "<a href=" + link + ">" + link + "</a>";
    }
}
