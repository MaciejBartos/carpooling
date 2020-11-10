package pl.lodz.p.edu.carpooling.CMS.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private String city;
    private String street;
    private Long houseNumber;
}
