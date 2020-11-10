package pl.lodz.p.edu.carpooling.CMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.exceptions.account.AccountDoesNotExistException;
import pl.lodz.p.edu.carpooling.persistance.entities.Account;
import pl.lodz.p.edu.carpooling.persistance.entities.Role;
import pl.lodz.p.edu.carpooling.persistance.entities.models.RoleEnum;
import pl.lodz.p.edu.carpooling.persistance.daos.AccountDAO;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class AccountService {

    private final AccountDAO accountDAO;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    public void registerAccount(Account account) {
        Role userRole = roleService.findRoleByRoleName(RoleEnum.ROLE_USER);

        account.setActive(true);
        account.setPassword(encoder.encode(account.getPassword()));
        account.getRoles().add(userRole);
        accountDAO.saveAndFlush(account);
    }

    public Account getAccountById(Long id) {
        return accountDAO.findById(id)
                .orElseThrow(AccountDoesNotExistException::accountDoesNotExistException);
    }
}
