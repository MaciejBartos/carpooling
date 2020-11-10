package pl.lodz.p.edu.carpooling.exceptions.role;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exceptions.BaseAppException;

public class RoleNotFoundException extends BaseAppException {

    public static final String EXCEPTION_MESSAGE = "Role not found exception";

    public RoleNotFoundException(HttpStatus status) {
        super(status);
    }

    public RoleNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public RoleNotFoundException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static RoleNotFoundException roleNotFoundException() {
        return new RoleNotFoundException(HttpStatus.NOT_FOUND, EXCEPTION_MESSAGE);
    }
}
