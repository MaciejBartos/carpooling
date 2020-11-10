package pl.lodz.p.edu.carpooling.CMS.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalDataDTO {

    private Long id;
    private String name;
    private String surname;
    private Long yearsOld;
    private Long version;
}
