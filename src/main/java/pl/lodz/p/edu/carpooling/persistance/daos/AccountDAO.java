package pl.lodz.p.edu.carpooling.persistance.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.edu.carpooling.persistance.entities.Account;

import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByLogin(String login);
}
