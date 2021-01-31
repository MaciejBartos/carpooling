package pl.lodz.p.edu.carpooling.module.service.customer.request;

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
