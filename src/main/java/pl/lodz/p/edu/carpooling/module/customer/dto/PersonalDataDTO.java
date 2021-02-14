package pl.lodz.p.edu.carpooling.module.customer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonalDataDTO {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Long version;
}
