package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class UpdateAccountRequest {

    private String id;
    private String email;
    private Long version;
}
