package pl.lodz.p.edu.carpooling.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.carpooling.exception.address.AddressException;
import pl.lodz.p.edu.carpooling.persistence.dao.AddressDAO;
import pl.lodz.p.edu.carpooling.persistence.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.MANDATORY)
public class AddressRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final AddressDAO addressDAO;

    public Address findById(Long id) {
        return addressDAO.findById(id).orElseThrow(AddressException::addressDoesNotExistException);
    }

    public void update(Address address) {
        entityManager.detach(address);
        addressDAO.saveAndFlush(address);
    }
}
