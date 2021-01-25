package pl.lodz.p.edu.carpooling.exception.validation;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class ValidationException extends BaseAppException {

    private static final String ACCOUNT_EMAIL_USED_VALIDATION_EXCEPTION = "account.email.used.validation.exception";
    private static final String ACCOUNT_LOGIN_USED_VALIDATION_EXCEPTION = "account.login.used.validation.exception";

    public ValidationException(HttpStatus status) {
        super(status);
    }

    public ValidationException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public ValidationException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static ValidationException accountEmailUsedValidationException() {
        return new ValidationException(HttpStatus.UNPROCESSABLE_ENTITY, ACCOUNT_EMAIL_USED_VALIDATION_EXCEPTION);
    }

    public static ValidationException accountLoginUsedValidationException() {
        return new ValidationException(HttpStatus.UNPROCESSABLE_ENTITY, ACCOUNT_LOGIN_USED_VALIDATION_EXCEPTION);
    }

}
