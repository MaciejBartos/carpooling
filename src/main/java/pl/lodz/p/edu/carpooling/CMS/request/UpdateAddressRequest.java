package pl.lodz.p.edu.carpooling.CMS.request;

import lombok.Data;

@Data
public class UpdateAddressRequest {

    private Long id;
    private String city;
    private String street;
    private Long houseNumber;
    private Long version;
}
