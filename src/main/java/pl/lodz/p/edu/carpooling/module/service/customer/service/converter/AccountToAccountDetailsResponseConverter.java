package pl.lodz.p.edu.carpooling.module.service.customer.service.converter;

import pl.lodz.p.edu.carpooling.module.service.customer.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.module.service.customer.dto.AddressDTO;
import pl.lodz.p.edu.carpooling.module.service.customer.dto.PersonalDataDTO;
import pl.lodz.p.edu.carpooling.module.service.customer.response.AccountDetailsResponse;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;

public class AccountToAccountDetailsResponseConverter {

    public static AccountDetailsResponse convert(Account account) {
        AccountDTO accountDTO = AccountDTO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .login(account.getLogin())
                .active(account.isActive())
                .version(account.getVersion())
                .build();
        PersonalDataDTO personalDataDTO = PersonalDataDTO.builder()
                .id(account.getPersonalData().getId())
                .name(account.getPersonalData().getName())
                .surname(account.getPersonalData().getSurname())
                .birthDate(account.getPersonalData().getBirthDate())
                .version(account.getPersonalData().getVersion())
                .build();
        AddressDTO addressDTO = AddressDTO.builder()
                .id(account.getPersonalData().getAddress().getId())
                .city(account.getPersonalData().getAddress().getCity())
                .street(account.getPersonalData().getAddress().getStreet())
                .houseNumber(account.getPersonalData().getAddress().getHouseNumber())
                .version(account.getPersonalData().getAddress().getVersion())
                .build();
        return AccountDetailsResponse.builder()
                .account(accountDTO)
                .address(addressDTO)
                .personalData(personalDataDTO)
                .build();
    }
}
