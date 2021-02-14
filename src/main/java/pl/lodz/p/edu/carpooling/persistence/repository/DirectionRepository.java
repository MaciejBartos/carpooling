package pl.lodz.p.edu.carpooling.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.carpooling.exception.direction.DirectionException;
import pl.lodz.p.edu.carpooling.module.carpooling.request.model.LatitudeLongitude;
import pl.lodz.p.edu.carpooling.persistence.dao.DirectionDAO;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.entity.Direction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.MANDATORY)
public class DirectionRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final DirectionDAO directionDAO;

    public Direction findById(Long id) {
        return directionDAO.findById(id).orElseThrow(DirectionException::directionDoesNotExistException);
    }

    public Direction findByIdThenLock(Long id) {
        return directionDAO.findByIdThenLock(id);
    }

    public List<Direction> findDirectionsWhenInRadiusOfGivenCoordinate(LatitudeLongitude origin,
                                                                       LatitudeLongitude destination,
                                                                       long distance, LocalDate travelDate) {
        return directionDAO.findDirectionsWhenInRadiusOfGivenCoordinate(origin.getLatitude(),
                origin.getLongitude(), destination.getLatitude(), destination.getLongitude(), distance,
                travelDate, travelDate.plusDays(1));
    }

    public void save(Direction direction) {
        directionDAO.saveAndFlush(direction);
    }

    public void update(Direction direction) {
        entityManager.detach(direction);
        directionDAO.saveAndFlush(direction);
    }

    public List<Direction> findAllDirectionsCreatedByCurrentUser(String login) {
        return directionDAO.findAllDirectionsCreatedByCurrentUser(login);
    }

    public List<Direction> findAllDirectionsAssignedToUser(Account account) {
        return directionDAO.findAllDirectionsAssignedToUser(account);
    }

}
