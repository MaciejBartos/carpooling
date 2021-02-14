package pl.lodz.p.edu.carpooling.module.carpooling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.exception.direction.DirectionException;
import pl.lodz.p.edu.carpooling.module.carpooling.request.AssignToDirectionRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.request.CreateDirectionRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.response.CreateDirectionResponse;
import pl.lodz.p.edu.carpooling.module.carpooling.response.GetDirectionDetailsResponse;
import pl.lodz.p.edu.carpooling.module.carpooling.response.model.DirectionDetailsForList;
import pl.lodz.p.edu.carpooling.module.carpooling.service.converter.DirectionToGetDirectionDetailsResponseConverter;
import pl.lodz.p.edu.carpooling.module.carpooling.request.GetDirectionForGivenCoordinatesRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.request.ResignFromDirectionRequest;
import pl.lodz.p.edu.carpooling.module.carpooling.service.converter.DirectionToDirectionDetailsForListConverter;
import pl.lodz.p.edu.carpooling.module.carpooling.service.converter.LatitudeLongitudeToCoordinatesConverter;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.entity.Direction;
import pl.lodz.p.edu.carpooling.persistence.entity.Vehicle;
import pl.lodz.p.edu.carpooling.persistence.repository.AccountRepository;
import pl.lodz.p.edu.carpooling.persistence.repository.DirectionRepository;
import pl.lodz.p.edu.carpooling.persistence.repository.VehicleRepository;
import pl.lodz.p.edu.carpooling.util.email.EmailService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class DirectionService {

    private final VehicleRepository vehicleRepository;
    private final AccountRepository accountRepository;
    private final DirectionRepository directionRepository;
    private final EmailService emailService;

    public void assignToDirection(AssignToDirectionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByLogin(authentication.getName());
        Direction direction = directionRepository.findByIdThenLock(Long.valueOf(request.getDirectionId()));

        validIfItIsPossibleToAssign(direction, account);

        direction.getGuests().add(account);
        directionRepository.save(direction);
    }

    private void validIfItIsPossibleToAssign(Direction direction, Account account) {
        if (direction.getGuests().size() == direction.getNumberOfAvailableSeats()) {
            throw DirectionException.cannotAssignThereIsNoAvailableSeatsException();
        }
        if (direction.getHost().getLogin().equals(account.getLogin())) {
            throw DirectionException.cannotAssignToDirectionCreatedByTheSameAccountException();
        }
        if (direction.getGuests().contains(account)) {
            throw DirectionException.alreadyAssignedToThisDirectionException();
        }
    }

    public void resignFromDirection(ResignFromDirectionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Direction direction = directionRepository.findById(Long.valueOf(request.getDirectionId()));
        Account account = accountRepository.findByLogin(authentication.getName());
        validIfItIsPossibleToResign(direction, account);

        direction.getGuests().remove(account);
        directionRepository.update(direction);
    }

    private void validIfItIsPossibleToResign(Direction direction, Account account) {
        if (!direction.getGuests().contains(account)) {
            throw DirectionException.cannotResignAccountNotAssignedToDirectionException();
        }
    }

    public CreateDirectionResponse createDirection(CreateDirectionRequest request) {
        Direction direction = prepareDirectionEntityToSave(request);
        validIfDirectionCanBeCreated(request);
        directionRepository.save(direction);

        return CreateDirectionResponse.builder()
                .directionId(direction.getId().toString())
                .build();
    }

    public void deleteDirection(String id) {
        Direction direction = directionRepository.findById(Long.valueOf(id));

        validIfCurrentUserCanDeleteDirection(direction);

        direction.setActive(false);
        direction.getGuests().forEach(assignedUser ->
                emailService.sendDirectionHasBeenDeletedByTheCreatorEmailForAssignedUser(assignedUser.getEmail(), direction.getId().toString()));
        for (int i = 0; i < direction.getGuests().size(); i++) {
            direction.getGuests().set(i, null);
        }

        directionRepository.update(direction);

    }

    private void validIfCurrentUserCanDeleteDirection(Direction direction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByLogin(authentication.getName());

        if (!account.getLogin().equals(direction.getHost().getLogin())) {
            throw DirectionException.cannotDeleteDirectionCurrentUserIsNotTheOwnerException();
        }
        if (!direction.isActive()) {
            throw DirectionException.directionAlreadyDeletedException();
        }
    }

    private void validIfDirectionCanBeCreated(CreateDirectionRequest request) {
        Vehicle vehicle = vehicleRepository.findById(Long.valueOf(request.getVehicleId()));
        if (!checkIfNumberOfAvailableSeatsFitsInTheNumberOfSeatsInVehicle(vehicle, request.getNumberOfFreeSeats())) {
            throw DirectionException.cannotCreateDirectionNumberOfAvailableSeatsIsNotValidException();
        }
        if (!checkIfTravelDateIsFutureDate(request.getTravelDate())) {
            throw DirectionException.cannotCreateDirectionTravelDateIsNotValidException();
        }

    }

    private boolean checkIfNumberOfAvailableSeatsFitsInTheNumberOfSeatsInVehicle(Vehicle vehicle, int numberOfAvailableSeats) {
        return vehicle.getNumberOfSeats() - 1 >= numberOfAvailableSeats;
    }

    private boolean checkIfTravelDateIsFutureDate(LocalDateTime travelDate) {
        return travelDate.isAfter(LocalDateTime.now());
    }

    public List<DirectionDetailsForList> getDirectionsAssignedToCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByLogin(authentication.getName());
        return directionRepository.findAllDirectionsAssignedToUser(account).stream()
                .map(DirectionToDirectionDetailsForListConverter::convert)
                .collect(Collectors.toList());
    }

    public List<DirectionDetailsForList> getDirectionsCreatedByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Direction> directions = directionRepository.findAllDirectionsCreatedByCurrentUser(authentication.getName());
        return directions.stream()
                .map(DirectionToDirectionDetailsForListConverter::convert)
                .collect(Collectors.toList());
    }

    public GetDirectionDetailsResponse getDirectionDetails(String id) {
        Direction direction = directionRepository.findById(Long.valueOf(id));
        GetDirectionDetailsResponse response = DirectionToGetDirectionDetailsResponseConverter.convert(direction);
        response.setAssignAction(checkIfCurrentUserCanAssign(direction));
        response.setResignAction(checkIfCurrentUserCanResign(direction));
        response.setNumberOfAvailableSeats(direction.getNumberOfAvailableSeats() - direction.getGuests().size());
        return response;
    }

    private boolean checkIfCurrentUserCanAssign(Direction direction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByLogin(authentication.getName());
        return !direction.getHost().getLogin().equals(account.getLogin()) &&
                !(direction.getGuests().contains(account)) &&
                (direction.getTravelDate().isAfter(LocalDateTime.now()));
    }

    private boolean checkIfCurrentUserCanResign(Direction direction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByLogin(authentication.getName());
        return !direction.getHost().getLogin().equals(account.getLogin()) &&
                direction.getGuests().contains(account) &&
                (direction.getTravelDate().isAfter(LocalDateTime.now()));
    }

    public List<DirectionDetailsForList> getDirections(GetDirectionForGivenCoordinatesRequest request) {
        return directionRepository.findDirectionsWhenInRadiusOfGivenCoordinate(
                request.getOrigin(), request.getDestination(), request.getDistance(), request.getTravelDate()).stream()
                .map(DirectionToDirectionDetailsForListConverter::convert)
                .collect(Collectors.toList());
    }

    private Direction prepareDirectionEntityToSave(CreateDirectionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Vehicle vehicle = vehicleRepository.findById(Long.valueOf(request.getVehicleId()));
        Account account = accountRepository.findByLogin(authentication.getName());

        return Direction.builder()
                .vehicle(vehicle)
                .host(account)
                .destination(LatitudeLongitudeToCoordinatesConverter.convert(request.getDestination()))
                .destinationAddress(request.getDestinationAddress())
                .origin(LatitudeLongitudeToCoordinatesConverter.convert(request.getOrigin()))
                .originAddress(request.getOriginAddress())
                .numberOfAvailableSeats(request.getNumberOfFreeSeats())
                .travelDate(request.getTravelDate())
                .waypoint(request.getWaypoints().stream()
                        .map(LatitudeLongitudeToCoordinatesConverter::convert)
                        .collect(Collectors.toList()))
                .active(true)
                .build();
    }

}
