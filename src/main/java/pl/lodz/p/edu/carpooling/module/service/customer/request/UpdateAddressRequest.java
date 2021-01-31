package pl.lodz.p.edu.carpooling.module.service.customer.request;

import lombok.Data;

@Data
public class UpdateAddressRequest {

    private String id;
    private String city;
    private String street;
    private Long houseNumber;
    private Long version;
}
