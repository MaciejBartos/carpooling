package pl.lodz.p.edu.carpooling.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.lodz.p.edu.carpooling.persistence.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleDAO extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findById(Long id);

    @Query("SELECT v FROM Vehicle v WHERE v.owner.login = :login")
    List<Vehicle> findAllByOwnerLogin(String login);
}
