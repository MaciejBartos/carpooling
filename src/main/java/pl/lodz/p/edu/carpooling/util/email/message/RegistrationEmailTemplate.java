package pl.lodz.p.edu.carpooling.util.email.message;

import lombok.Getter;

@Getter
public class RegistrationEmailTemplate implements EmailTemplate{

    public String subject;
    public String body;

    RegistrationEmailTemplate(String confirmAccountToken) {
        String link = "https://localhost:8080/registration/confirm?token=" + confirmAccountToken;
        subject = "Confirm your account!";
        body = "To finalize registration process you have to confirm your account by clicking in the link below <br/>" +
                "<a href=" + link + ">" + link + "</a>";
    }
}
