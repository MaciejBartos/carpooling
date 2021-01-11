package pl.lodz.p.edu.carpooling.CMS.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.CMS.dto.AccountDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.AddressDTO;
import pl.lodz.p.edu.carpooling.CMS.dto.PersonalDataDTO;

@Data
@Builder
public class AccountDetailsResponse {
    private AccountDTO account;
    private PersonalDataDTO personalData;
    private AddressDTO address;
}
