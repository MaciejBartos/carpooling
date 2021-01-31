package pl.lodz.p.edu.carpooling.module.service.customer.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.module.service.customer.response.model.AccountDetailsForList;

import java.util.List;

@Data
@Builder
public class GetAccountsResponse {

    private List<AccountDetailsForList> accounts;
}
