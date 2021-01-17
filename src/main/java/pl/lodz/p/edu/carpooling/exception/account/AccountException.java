package pl.lodz.p.edu.carpooling.exception.account;


import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class AccountException extends BaseAppException {

    private static final String ACCOUNT_DOES_NOT_EXIST = "account-does-not-exist";
    private static final String SAME_PASSWORDS = "account-new-password-same-as-old";
    private static final String WRONG_CURRENT_PASSWORD = "account-wrong-current-password";
    private static final String REPEAT_PASSWORD_DIFFERS_FROM_NEW_PASSWORD = "account-repeat-password-differs-from-new-password";
    private static final String EMAIL_TOKEN_EXPIRED = "account-confirmation-token-expired";

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
        return new AccountException(HttpStatus.UNPROCESSABLE_ENTITY, SAME_PASSWORDS);
    }

    public static AccountException wrongCurrentPasswordException() {
        return new AccountException(HttpStatus.UNPROCESSABLE_ENTITY, WRONG_CURRENT_PASSWORD);
    }

    public static AccountException repeatPasswordDiffersFromNewPasswordException() {
        return new AccountException(HttpStatus.UNPROCESSABLE_ENTITY, REPEAT_PASSWORD_DIFFERS_FROM_NEW_PASSWORD);
    }

    public static AccountException emailTokenExpiredException() {
        return new AccountException(HttpStatus.UNPROCESSABLE_ENTITY, EMAIL_TOKEN_EXPIRED);
    }
}
