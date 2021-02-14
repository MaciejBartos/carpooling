package pl.lodz.p.edu.carpooling.exception.direction;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class DirectionException extends BaseAppException {

    private static final String DIRECTION_DOES_NOT_EXIST = "direction-does-not-exist";
    private static final String CANNOT_ASSIGN_TO_DIRECTION_CREATED_BY_THE_SAME_ACCOUNT
            = "cannot-assign-to-direction-created-by-the-same-account";
    private static final String CANNOT_ASSIGN_THERE_IS_NO_AVAILABLE_SEATS = "cannot-assign-there-is-not-available-seats";
    private static final String CANNOT_CREATE_DIRECTION_NUMBER_OF_AVAILABLE_SEATS_IN_NOT_VALID
            = "cannot-create-direction-number-of-seats-is-not-valid";
    private static final String CANNOT_CREATE_DIRECTION_TRAVEL_DATE_IS_NOT_VALID
            = "cannot-create-direction-travel-date-is-not-valid";
    private static final String ALREADY_ASSIGNED_TO_THIS_DIRECTION = "already-assigned-to-this-direction";
    private static final String CANNOT_RESIGN_ACCOUNT_NOT_ASSIGNED_TO_DIRECTION
            = "cannot-resign-account-not-assigned-to-direction";
    private static final String CANNOT_DELETE_DIRECTION_CURRENT_USER_IS_NOT_THE_CREATOR
            = "cannot-delete-direction-current-user-is-not-the-creator";
    private static final String DIRECTION_ALREADY_DELETED = "direction-already-deleted";


    public DirectionException(HttpStatus status) {
        super(status);
    }

    public DirectionException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public DirectionException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static DirectionException directionDoesNotExistException() {
        return new DirectionException(HttpStatus.NOT_FOUND, DIRECTION_DOES_NOT_EXIST);
    }

    public static DirectionException cannotAssignToDirectionCreatedByTheSameAccountException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, CANNOT_ASSIGN_TO_DIRECTION_CREATED_BY_THE_SAME_ACCOUNT);
    }

    public static DirectionException cannotAssignThereIsNoAvailableSeatsException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, CANNOT_ASSIGN_THERE_IS_NO_AVAILABLE_SEATS);
    }

    public static DirectionException cannotCreateDirectionNumberOfAvailableSeatsIsNotValidException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, CANNOT_CREATE_DIRECTION_NUMBER_OF_AVAILABLE_SEATS_IN_NOT_VALID);
    }

    public static DirectionException cannotCreateDirectionTravelDateIsNotValidException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, CANNOT_CREATE_DIRECTION_TRAVEL_DATE_IS_NOT_VALID);
    }

    public static DirectionException alreadyAssignedToThisDirectionException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, ALREADY_ASSIGNED_TO_THIS_DIRECTION);
    }

    public static DirectionException cannotResignAccountNotAssignedToDirectionException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, CANNOT_RESIGN_ACCOUNT_NOT_ASSIGNED_TO_DIRECTION);
    }

    public static DirectionException cannotDeleteDirectionCurrentUserIsNotTheOwnerException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, CANNOT_DELETE_DIRECTION_CURRENT_USER_IS_NOT_THE_CREATOR);
    }

    public static DirectionException directionAlreadyDeletedException() {
        return new DirectionException(HttpStatus.UNPROCESSABLE_ENTITY, DIRECTION_ALREADY_DELETED);
    }
}
