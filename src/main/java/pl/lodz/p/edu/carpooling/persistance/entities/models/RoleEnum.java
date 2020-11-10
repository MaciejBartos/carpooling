package pl.lodz.p.edu.carpooling.persistance.entities.models;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_DRIVER("DRIVER");

    private final String roleDb;

    RoleEnum(String roleDb) {
        this.roleDb = roleDb;
    }
}
