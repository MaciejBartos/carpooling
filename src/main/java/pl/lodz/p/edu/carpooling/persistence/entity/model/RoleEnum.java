package pl.lodz.p.edu.carpooling.persistence.entity.model;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ROLE_USER("USER", "User"),
    ROLE_ADMIN("ADMIN", "Admin");

    private final String roleDb;
    private final String roleView;

    RoleEnum(String roleDb, String roleView) {
        this.roleDb = roleDb;
        this.roleView = roleView;
    }

    public static RoleEnum fromStringRoleView(String roleView) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getRoleView().equals(roleView)) {
                return role;
            }
        }
        return null;
    }
}
