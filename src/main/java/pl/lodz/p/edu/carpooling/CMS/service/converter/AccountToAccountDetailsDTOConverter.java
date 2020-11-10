package pl.lodz.p.edu.carpooling.CMS.service.converter;

import pl.lodz.p.edu.carpooling.CMS.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDetailsDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.AddressDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.PersonalDataDTO;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;

public class AccountToAccountDetailsDTOConverter {

    public static AccountDetailsDTO entityToDto(Account account) {
        AccountDTO accountDTO = AccountDTO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .login(account.getLogin())
                .version(account.getVersion())
                .build();
        PersonalDataDTO personalDataDTO = PersonalDataDTO.builder()
                .id(account.getPersonalData().getId())
                .name(account.getPersonalData().getName())
                .surname(account.getPersonalData().getSurname())
                .yearsOld(account.getPersonalData().getYearsOld())
                .version(account.getPersonalData().getVersion())
                .build();
        AddressDTO addressDTO = AddressDTO.builder()
                .id(account.getPersonalData().getAddress().getId())
                .city(account.getPersonalData().getAddress().getCity())
                .street(account.getPersonalData().getAddress().getStreet())
                .houseNumber(account.getPersonalData().getAddress().getHouseNumber())
                .version(account.getPersonalData().getAddress().getVersion())
                .build();
        return AccountDetailsDTO.builder()
                .account(accountDTO)
                .address(addressDTO)
                .personalData(personalDataDTO)
                .build();
    }
}