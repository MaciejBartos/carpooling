package pl.lodz.p.edu.carpooling.util.email.message;

import lombok.Getter;

@Getter
public class ResetPasswordEmailTemplate implements EmailTemplate {

    private final String subject;
    private final String body;

    ResetPasswordEmailTemplate(String token, String address) {
        String link = address + "/reset-password?token=" + token;
        subject = "Reset password in carpooling app";
        body = "Click in the link below to change current password <br/>" +
                "<a href=" + link + ">" + link + "</a>";
    }
}
