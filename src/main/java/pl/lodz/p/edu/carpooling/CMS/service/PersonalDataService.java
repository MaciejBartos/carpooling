package pl.lodz.p.edu.carpooling.CMS.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.CMS.request.UpdatePersonalDataRequest;
import pl.lodz.p.edu.carpooling.persistence.entity.PersonalData;
import pl.lodz.p.edu.carpooling.persistence.repository.PersonalDataRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class PersonalDataService {

    private final PersonalDataRepository personalDataRepository;

    public void update(UpdatePersonalDataRequest personalDataDTO) {
        PersonalData personalData = personalDataRepository.findById(Long.parseLong(personalDataDTO.getId()));
        personalData.setName(personalDataDTO.getName());
        personalData.setSurname(personalDataDTO.getSurname());
        personalData.setBirthDate(personalDataDTO.getBirthDate());
        personalData.setVersion(personalDataDTO.getVersion());
        personalDataRepository.update(personalData);
    }

}
