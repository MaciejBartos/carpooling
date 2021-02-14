package pl.lodz.p.edu.carpooling.module.customer.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.carpooling.module.customer.response.AvailableRolesResponse;
import pl.lodz.p.edu.carpooling.module.customer.request.UpdateUserRolesRequest;
import pl.lodz.p.edu.carpooling.module.customer.service.RoleService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/role")
@RequiredArgsConstructor
public class RoleHttpEndpoint {

    private final RoleService roleService;

    @PutMapping(path = "/account")
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_ADMIN")
    public void updateAccountRoles(@Valid @RequestBody UpdateUserRolesRequest request) {
        roleService.updateUserRoles(request);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_ADMIN")
    public AvailableRolesResponse getAllAvailableRolesInSystem() {
        return AvailableRolesResponse.builder()
                .roles(roleService.getAllAvailableRoles())
                .build();
    }
}
