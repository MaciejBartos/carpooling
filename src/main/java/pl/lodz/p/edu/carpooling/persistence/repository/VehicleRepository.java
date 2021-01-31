package pl.lodz.p.edu.carpooling.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.carpooling.exception.vehicle.VehicleException;
import pl.lodz.p.edu.carpooling.persistence.dao.VehicleDAO;
import pl.lodz.p.edu.carpooling.persistence.entity.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.MANDATORY)
public class VehicleRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final VehicleDAO vehicleDAO;

    public Vehicle findById(Long id) {
        return vehicleDAO.findById(id).orElseThrow(VehicleException::vehicleDoesNotExistException);
    }

    public List<Vehicle> findByAccountLogin(String login) {
        return vehicleDAO.findAllByOwnerLogin(login);
    }

    public void save(Vehicle vehicle) {
        vehicleDAO.saveAndFlush(vehicle);
    }

    public void update(Vehicle vehicle) {
        entityManager.detach(vehicle);
        vehicleDAO.saveAndFlush(vehicle);
    }

}
