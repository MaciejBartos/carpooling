package pl.lodz.p.edu.carpooling.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.carpooling.exception.role.RoleException;
import pl.lodz.p.edu.carpooling.persistence.dao.RoleDAO;
import pl.lodz.p.edu.carpooling.persistence.entity.Role;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnum;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleRepository {

    private final RoleDAO roleDAO;

    public Role findRoleByName(RoleEnum name) {
        return roleDAO.findRoleByName(name).orElseThrow(RoleException::roleNotFoundException);
    }

    public List<Role> getAll() {
        return roleDAO.findAll();
    }
}
