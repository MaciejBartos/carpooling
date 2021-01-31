package pl.lodz.p.edu.carpooling.module.service.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.exception.role.RoleException;
import pl.lodz.p.edu.carpooling.persistence.entity.Role;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnum;
import pl.lodz.p.edu.carpooling.persistence.dao.RoleDAO;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDAO roleDAO;

    public Role findRoleByRoleName(RoleEnum role) {
        return roleDAO.findRoleByName(role).orElseThrow(RoleException::roleNotFoundException);
    }
}
