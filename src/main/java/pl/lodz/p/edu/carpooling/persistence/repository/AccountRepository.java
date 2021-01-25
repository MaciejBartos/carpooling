package pl.lodz.p.edu.carpooling.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.carpooling.exception.account.AccountException;
import pl.lodz.p.edu.carpooling.exception.validation.ValidationException;
import pl.lodz.p.edu.carpooling.persistence.dao.AccountDAO;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.MANDATORY)
public class AccountRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final AccountDAO accountDAO;

    public Account findByResetPasswordToken(String token) {
        return accountDAO.findByResetPasswordEmailToken(token)
                .orElseThrow(AccountException::accountDoesNotExistException);
    }

    public Account findByEmail(String email) {
        return accountDAO.findByEmail(email).orElseThrow(AccountException::accountDoesNotExistException);
    }

    public Account findById(Long id) {
        return accountDAO.findById(id).orElseThrow(AccountException::accountDoesNotExistException);
    }

    public Account findByLogin(String login) {
        return accountDAO.findByLogin(login).orElseThrow(AccountException::accountDoesNotExistException);
    }

    public Account findByConfirmationToken(String confirmationToken) {
        return accountDAO.findByConfirmationEmailToken(confirmationToken)
                .orElseThrow(AccountException::accountDoesNotExistException);
    }

    public List<Account> findAllWithSearchCriteria(String searchCriteria) {
        return accountDAO.findAllWithSearchCriteria(searchCriteria);
    }

    public void save(Account account) {
        try {
            accountDAO.saveAndFlush(account);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                String validationMessage = e.getCause().getCause().getMessage();
                if (validationMessage.contains("email")) {
                    throw ValidationException.accountEmailUsedValidationException();
                } else if (validationMessage.contains("login")) {
                    throw ValidationException.accountLoginUsedValidationException();
                }
            }
            throw e;
        }
    }

    public void update(Account account) {
        entityManager.detach(account);
        accountDAO.saveAndFlush(account);
    }
}
