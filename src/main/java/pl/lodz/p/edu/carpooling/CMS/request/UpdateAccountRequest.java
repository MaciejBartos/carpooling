package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class UpdateAccountRequest {

    private Long id;
    private String email;
    private Long version;
}
