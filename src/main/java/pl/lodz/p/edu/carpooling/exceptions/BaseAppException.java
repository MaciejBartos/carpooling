package pl.lodz.p.edu.carpooling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BaseAppException extends ResponseStatusException {

    public BaseAppException(HttpStatus status) {
        super(status);
    }

    public BaseAppException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public BaseAppException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
