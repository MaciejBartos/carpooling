package pl.lodz.p.edu.carpooling.CMS.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.carpooling.CMS.request.UpdateAddressRequest;
import pl.lodz.p.edu.carpooling.persistence.entity.Address;
import pl.lodz.p.edu.carpooling.persistence.repository.AddressRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class AddressService {

    private final AddressRepository addressRepository;

    public void update(UpdateAddressRequest addressDTO) {
        Address address = addressRepository.findById(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setStreet(addressDTO.getStreet());
        address.setVersion(addressDTO.getVersion());
        addressRepository.update(address);
    }
}
