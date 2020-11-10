package pl.lodz.p.edu.carpooling.CMS.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTO {

    private Long id;
    private String login;
    private String email;
    private PersonalDataDTO personalData;
}
