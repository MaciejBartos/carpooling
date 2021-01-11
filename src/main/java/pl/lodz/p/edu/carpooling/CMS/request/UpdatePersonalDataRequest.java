package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class UpdatePersonalDataRequest {

    private Long id;
    private String name;
    private String surname;
    private Long yearsOld;
    private Long version;
}
