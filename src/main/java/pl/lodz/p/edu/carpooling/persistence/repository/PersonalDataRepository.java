package pl.lodz.p.edu.carpooling.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.carpooling.exception.personaldata.PersonalDataException;
import pl.lodz.p.edu.carpooling.persistence.dao.PersonalDataDAO;
import pl.lodz.p.edu.carpooling.persistence.entity.PersonalData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.MANDATORY)
public class PersonalDataRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final PersonalDataDAO personalDataDAO;

    public void update(PersonalData personalData) {
        entityManager.detach(personalData);
        personalDataDAO.saveAndFlush(personalData);
    }

    public PersonalData findById(Long id) {
        return personalDataDAO.findById(id).orElseThrow(PersonalDataException::personalDataDoesNotExist);
    }
}
