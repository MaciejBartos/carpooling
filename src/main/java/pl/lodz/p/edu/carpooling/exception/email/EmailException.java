package pl.lodz.p.edu.carpooling.exception.email;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class EmailException extends BaseAppException {

    private static final String SENDING_EMAIL_ERROR = "sending.email.error";

    public EmailException(HttpStatus status) {
        super(status);
    }

    public EmailException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public EmailException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static EmailException createSendingEmailErrorException(Throwable cause) {
        return new EmailException(HttpStatus.INTERNAL_SERVER_ERROR, SENDING_EMAIL_ERROR, cause);
    }
}
