package pl.lodz.p.edu.carpooling.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.persistance.entities.Account;
import pl.lodz.p.edu.carpooling.persistance.daos.AccountDAO;
import pl.lodz.p.edu.carpooling.security.models.AccountDetails;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final AccountDAO accountDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDAO.findAccountByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with username: " + username));

        return AccountDetails.build(account);
    }
}
