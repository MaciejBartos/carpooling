package pl.lodz.p.edu.carpooling.module.service.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDetailsDTO {

    private AccountDTO account;
    private PersonalDataDTO personalData;
    private AddressDTO address;
}
