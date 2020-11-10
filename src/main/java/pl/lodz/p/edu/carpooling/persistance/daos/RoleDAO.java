package pl.lodz.p.edu.carpooling.persistance.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.edu.carpooling.persistance.entities.Role;
import pl.lodz.p.edu.carpooling.persistance.entities.models.RoleEnum;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(RoleEnum roleName);
}
