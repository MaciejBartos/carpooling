package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnum;
import pl.lodz.p.edu.carpooling.persistence.entity.model.RoleEnumConverter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @Convert(converter = RoleEnumConverter.class)
    private RoleEnum name;

    private String description;
}
