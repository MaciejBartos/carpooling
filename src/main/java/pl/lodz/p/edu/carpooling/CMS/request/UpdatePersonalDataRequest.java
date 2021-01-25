package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePersonalDataRequest {

    private String id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Long version;
}
