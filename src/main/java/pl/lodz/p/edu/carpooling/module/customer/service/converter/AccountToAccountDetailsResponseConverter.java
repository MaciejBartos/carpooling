package pl.lodz.p.edu.carpooling.module.customer.service.converter;

import pl.lodz.p.edu.carpooling.module.customer.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.module.customer.dto.AddressDTO;
import pl.lodz.p.edu.carpooling.module.customer.dto.PersonalDataDTO;
import pl.lodz.p.edu.carpooling.module.customer.response.AccountDetailsResponse;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;
import pl.lodz.p.edu.carpooling.persistence.entity.Role;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnum;

import java.util.stream.Collectors;

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
                .roles(account.getRoles().stream()
                        .map(Role::getName)
                        .map(RoleEnum::getRoleView)
                        .collect(Collectors.toList()))
                .build();
    }
}
