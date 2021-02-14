package pl.lodz.p.edu.carpooling.module.carpooling.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDirectionResponse {

    private final String directionId;
}
