package pl.lodz.p.edu.carpooling.module.carpooling.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateVehicleRequest {

    @Pattern(regexp = "^[A-Za-z0-9]*$")
    @NotEmpty
    private String brand;
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    @NotEmpty
    private String model;
    @NotNull
    private long productionYear;
    private String description;
    @NotNull
    private int numberOfSeats;
}
