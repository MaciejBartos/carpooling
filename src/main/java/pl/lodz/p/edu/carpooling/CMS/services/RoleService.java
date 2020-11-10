package pl.lodz.p.edu.carpooling.CMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.exceptions.role.RoleNotFoundException;
import pl.lodz.p.edu.carpooling.persistance.entities.Role;
import pl.lodz.p.edu.carpooling.persistance.entities.models.RoleEnum;
import pl.lodz.p.edu.carpooling.persistance.daos.RoleDAO;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDAO roleDAO;

    public Role findRoleByRoleName(RoleEnum role) {
        return roleDAO.findRoleByName(role).orElseThrow(RoleNotFoundException::roleNotFoundException);
    }
}
