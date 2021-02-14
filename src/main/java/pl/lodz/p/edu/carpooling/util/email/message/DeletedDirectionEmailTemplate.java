package pl.lodz.p.edu.carpooling.util.email.message;

import lombok.Getter;

@Getter
public class DeletedDirectionEmailTemplate implements EmailTemplate {

    private final String subject;
    private final String body;

    DeletedDirectionEmailTemplate(String directionId, String frontendAddress) {
        String link = frontendAddress + "/map/" + directionId;

        subject = "Direction deleted";
        body = "Unfortunately the direction you have signed up has been deleted, here is the link with this direction details <br/>" +
                "<a href=" + link + ">link</a>";
    }
}
