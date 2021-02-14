package pl.lodz.p.edu.carpooling.persistence.entity.model;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Optional;

public class RoleEnumConverter implements AttributeConverter<RoleEnum, String> {

    @Override
    public String convertToDatabaseColumn(RoleEnum attribute) {
        return attribute.getRoleDb();
    }

    @Override
    public RoleEnum convertToEntityAttribute(String dbData) {
        Optional<RoleEnum> roleDb = Arrays.stream(RoleEnum.values())
                .filter(role -> role.getRoleDb().equals(dbData))
                .findFirst();
        return roleDb.orElseThrow(() -> new RuntimeException("No such enum for value: " + dbData));
    }
}
