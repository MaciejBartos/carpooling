package pl.lodz.p.edu.carpooling.exception.address;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class AddressException extends BaseAppException {

    private static final String ACCOUNT_DOES_NOT_EXIST = "address-does-not-exist";

    public AddressException(HttpStatus status) {
        super(status);
    }

    public AddressException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public AddressException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static AddressException addressDoesNotExistException() {
        return new AddressException(HttpStatus.NOT_FOUND, ACCOUNT_DOES_NOT_EXIST);
    }
}
