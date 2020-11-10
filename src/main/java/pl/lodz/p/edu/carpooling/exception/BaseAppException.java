package pl.lodz.p.edu.carpooling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BaseAppException extends ResponseStatusException {

    public static final String OPTIMISTIC_LOCK = "Optimistic lock occurred";

    public BaseAppException(HttpStatus status) {
        super(status);
    }

    public BaseAppException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public BaseAppException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static BaseAppException createOptimisticLockException() {
        return new BaseAppException(HttpStatus.CONFLICT, OPTIMISTIC_LOCK);
    }
}
