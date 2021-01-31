package pl.lodz.p.edu.carpooling.module.service.customer.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.module.service.customer.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.module.service.customer.dto.AddressDTO;
import pl.lodz.p.edu.carpooling.module.service.customer.dto.PersonalDataDTO;

@Data
@Builder
public class AccountDetailsResponse {
    private AccountDTO account;
    private PersonalDataDTO personalData;
    private AddressDTO address;
}
