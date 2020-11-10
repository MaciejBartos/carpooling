package pl.lodz.p.edu.carpooling.persistance.entities;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import pl.lodz.p.edu.carpooling.persistance.entities.models.RoleEnum;
import pl.lodz.p.edu.carpooling.persistance.entities.models.RoleEnumConverter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@Immutable
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @Convert(converter = RoleEnumConverter.class)
    private RoleEnum name;

    private String description;
}
