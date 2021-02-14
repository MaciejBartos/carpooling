package pl.lodz.p.edu.carpooling.module.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private Long id;
    private String city;
    private String street;
    private Long houseNumber;
    private Long version;
}
