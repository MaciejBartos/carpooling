package pl.lodz.p.edu.carpooling.exceptions.account;


import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exceptions.BaseAppException;

public class AccountDoesNotExistException extends BaseAppException {

    private static final String EXCEPTION_MESSAGE = "Account does not exist!";

    public AccountDoesNotExistException(HttpStatus status) {
        super(status);
    }

    public AccountDoesNotExistException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public AccountDoesNotExistException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static AccountDoesNotExistException accountDoesNotExistException() {
        return new AccountDoesNotExistException(HttpStatus.NOT_FOUND, EXCEPTION_MESSAGE);
    }
}
