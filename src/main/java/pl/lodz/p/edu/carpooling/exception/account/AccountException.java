package pl.lodz.p.edu.carpooling.exception.account;


import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class AccountException extends BaseAppException {

    private static final String ACCOUNT_DOES_NOT_EXIST = "Account does not exist!";
    private static final String SAME_PASSWORDS = "You cannot change password to the same";

    public AccountException(HttpStatus status) {
        super(status);
    }

    public AccountException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public AccountException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static AccountException accountDoesNotExistException() {
        return new AccountException(HttpStatus.NOT_FOUND, ACCOUNT_DOES_NOT_EXIST);
    }

    public static AccountException changeToTheSamePasswordException() {
        return new AccountException(HttpStatus.CONFLICT, SAME_PASSWORDS);
    }
}
