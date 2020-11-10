package pl.lodz.p.edu.carpooling.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;

import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByLogin(String login);
}
