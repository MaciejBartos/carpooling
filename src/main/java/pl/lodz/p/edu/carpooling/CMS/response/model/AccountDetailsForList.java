package pl.lodz.p.edu.carpooling.CMS.response.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDetailsForList {

    private String id;
    private String login;
    private String email;
    private String name;
    private String surname;
    private boolean active;

}
