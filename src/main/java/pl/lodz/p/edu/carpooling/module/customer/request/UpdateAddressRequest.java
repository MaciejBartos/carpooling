package pl.lodz.p.edu.carpooling.module.customer.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateAddressRequest {

    @NotEmpty
    @Pattern(regexp = "^\\d$")
    private String id;
    @Size(max = 64)
    @Pattern(regexp = "^[A-Za-zążźęćół ]+$")
    @NotEmpty
    private String city;
    @Size(max = 64)
    @Pattern(regexp = "^[A-Za-zążźęćół ]+$")
    @NotEmpty
    private String street;
    @Size(max = 10)
    @Pattern(regexp = "^[1-9][0-9]*")
    @NotEmpty
    private Long houseNumber;
    @NotEmpty
    private Long version;
}
