package pl.lodz.p.edu.carpooling.CMS.response;

import lombok.Builder;
import lombok.Data;
import pl.lodz.p.edu.carpooling.CMS.response.model.AccountDetailsForList;

import java.util.List;

@Data
@Builder
public class GetAccountsResponse {

    private List<AccountDetailsForList> accounts;
}
