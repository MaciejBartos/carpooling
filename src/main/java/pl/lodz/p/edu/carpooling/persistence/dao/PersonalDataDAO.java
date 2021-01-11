package pl.lodz.p.edu.carpooling.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.edu.carpooling.persistence.entity.PersonalData;

public interface PersonalDataDAO extends JpaRepository<PersonalData, Long> {
}
