package pl.lodz.p.edu.carpooling.module.customer.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.module.customer.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.module.customer.dto.AddressDTO;
import pl.lodz.p.edu.carpooling.module.customer.dto.PersonalDataDTO;

import java.util.List;

@Data
@Builder
public class AccountDetailsResponse {
    private AccountDTO account;
    private PersonalDataDTO personalData;
    private AddressDTO address;
    private List<String> roles;
}
