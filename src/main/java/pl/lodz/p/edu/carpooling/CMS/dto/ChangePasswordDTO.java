package pl.lodz.p.edu.carpooling.CMS.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private Long accountId;
    private String password;
    private Long version;
}
