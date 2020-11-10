package pl.lodz.p.edu.carpooling.CMS.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalDataDTO {

    private String name;
    private String surname;
    private Long yearsOld;
    private AddressDTO addressDTO;
}
