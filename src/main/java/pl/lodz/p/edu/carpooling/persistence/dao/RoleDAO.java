package pl.lodz.p.edu.carpooling.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.edu.carpooling.persistence.entity.Role;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnum;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(RoleEnum roleName);
}
