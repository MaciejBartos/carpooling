package pl.lodz.p.edu.carpooling.CMS.service.converter;

import lombok.Data;
import pl.lodz.p.edu.carpooling.CMS.response.model.AccountDetailsForList;
import pl.lodz.p.edu.carpooling.persistence.entity.Account;

@Data
public class AccountToAccountDetailsForListConverter {

    public static AccountDetailsForList convert(Account account) {
        return AccountDetailsForList.builder()
                .id(String.valueOf(account.getId()))
                .name(account.getPersonalData().getName())
                .surname(account.getPersonalData().getSurname())
                .login(account.getLogin())
                .email(account.getEmail())
                .active(account.isActive())
                .build();
    }
}
