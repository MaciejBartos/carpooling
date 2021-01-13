package pl.lodz.p.edu.carpooling.CMS.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTO {

    private Long id;
    private String login;
    private String email;
    private boolean active;
    private Long version;
}
