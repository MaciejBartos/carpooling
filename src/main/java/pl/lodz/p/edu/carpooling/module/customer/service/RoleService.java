package pl.lodz.p.edu.carpooling.module.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.module.customer.request.UpdateUserRolesRequest;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.entity.Role;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnum;
import pl.lodz.p.edu.carpooling.persistence.repository.AccountRepository;
import pl.lodz.p.edu.carpooling.persistence.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class RoleService {

    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    public void updateUserRoles(UpdateUserRolesRequest request) {
        Account account = accountRepository.findById(Long.valueOf(request.getAccountId()));
        List<Role> roles = request.getRoles().stream()
                .map(role -> roleRepository.findRoleByName(RoleEnum.fromStringRoleView(role)))
                .collect(Collectors.toList());
        account.setRoles(roles);
        accountRepository.update(account);
    }

    public List<String> getAllAvailableRoles() {
        return roleRepository.getAll().stream()
                .map(role -> role.getName().getRoleView())
                .collect(Collectors.toList());
    }
}
