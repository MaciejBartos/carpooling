package pl.lodz.p.edu.carpooling.exception.personaldata;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;
import pl.lodz.p.edu.carpooling.exception.account.AccountException;

public class PersonalDataException extends BaseAppException {

    private static final String PERSONAL_DATA_DOES_NOT_EXIST = "personal-data-does-not-exist";

    public PersonalDataException(HttpStatus status) {
        super(status);
    }

    public PersonalDataException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public PersonalDataException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static AccountException personalDataDoesNotExist() {
        return new AccountException(HttpStatus.NOT_FOUND, PERSONAL_DATA_DOES_NOT_EXIST);
    }
}
