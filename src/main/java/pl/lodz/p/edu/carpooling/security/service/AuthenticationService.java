package pl.lodz.p.edu.carpooling.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.dao.AccountDAO;
import pl.lodz.p.edu.carpooling.security.model.AccountDetails;

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
