package pl.lodz.p.edu.carpooling.exception.role;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class RoleException extends BaseAppException {

    public static final String EXCEPTION_MESSAGE = "Role not found exception";

    public RoleException(HttpStatus status) {
        super(status);
    }

    public RoleException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public RoleException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static RoleException roleNotFoundException() {
        return new RoleException(HttpStatus.NOT_FOUND, EXCEPTION_MESSAGE);
    }
}
