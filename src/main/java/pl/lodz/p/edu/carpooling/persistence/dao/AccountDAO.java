package pl.lodz.p.edu.carpooling.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, Long> {

    Optional<Account> findByLogin(String login);

    Optional<Account> findByEmailToken(String confirmationToken);

    @Query("SELECT a FROM Account a WHERE a.login like %:searchCriteria% or a.email like %:searchCriteria% or a.personalData.name like %:searchCriteria% or a.personalData.surname like %:searchCriteria%")
    List<Account> findAllWithSearchCriteria(String searchCriteria);
}
