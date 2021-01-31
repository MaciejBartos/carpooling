package pl.lodz.p.edu.carpooling.exception.vehicle;

import org.springframework.http.HttpStatus;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

public class VehicleException extends BaseAppException {

    private static final String VEHICLE_DOES_NOT_EXIST = "vehicle-does-not-exist";
    private static final String VEHICLE_IS_NOT_CONNECTED_WITH_ACCOUNT = "vehicle-is-not-connected-with-account";

    public VehicleException(HttpStatus status) {
        super(status);
    }

    public VehicleException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public VehicleException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static VehicleException vehicleDoesNotExistException() {
        return new VehicleException(HttpStatus.NOT_FOUND, VEHICLE_DOES_NOT_EXIST);
    }

    public static VehicleException vehicleIsNotConnectedWithAccountException() {
        return new VehicleException(HttpStatus.UNPROCESSABLE_ENTITY, VEHICLE_IS_NOT_CONNECTED_WITH_ACCOUNT);
    }

}
