package pl.lodz.p.edu.carpooling.module.carpooling.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.module.carpooling.response.model.DirectionDetailsForList;

import java.util.List;

@Data
@Builder
public class GetDirectionsResponse {

    List<DirectionDetailsForList> directions;

}
