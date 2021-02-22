package pl.lodz.p.edu.carpooling.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.entity.Direction;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;

public interface DirectionDAO extends JpaRepository<Direction, Long> {

    @Query(value = "select * from direction d " +
            "inner join coordinate c1 on d.origin_id = c1.id " +
            "inner join coordinate c2 on d.destination_id = c2.id " +
            "where (acos(sin(?1 * PI() / 180) * sin(c1.latitude * PI() / 180) + cos(?1 * PI() / 180) * cos(c1.latitude * PI() / 180) " +
            "* cos(abs(?2 - c1.longitude) * PI() / 180)) * 6371) <= ?5 and " +
            "(acos(sin(?3 * PI() / 180) * sin(c2.latitude * PI() / 180) + cos(?3 * PI() / 180) * cos(c2.latitude * PI() / 180) " +
            "* cos(abs(?4 - c2.longitude) * PI() / 180)) * 6371) <= ?5 and d.travel_date > ?6 and d.travel_date <= ?7 and d.active = true " +
            "order by d.travel_date"
            , nativeQuery = true)
    List<Direction> findDirectionsWhenInRadiusOfGivenCoordinate(double latitude1, double longitude1,
                                                                double latitude2, double longitude2,
                                                                long distance,
                                                                LocalDate travelFrom, LocalDate travelTo);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select d from Direction d where d.id = ?1")
    Direction findByIdThenLock(Long id);

    @Query(value = "select d from Direction d " +
            "where d.host.login = ?1 and d.active = true " +
            "order by d.travelDate")
    List<Direction> findAllDirectionsCreatedByCurrentUser(String login);

    @Query(value = "select d from Direction d " +
            "where ?1 member d.guests and d.travelDate > CURRENT_TIMESTAMP and d.active = true " +
            "order by d.travelDate")
    List<Direction> findAllDirectionsAssignedToUser(Account account);
}
