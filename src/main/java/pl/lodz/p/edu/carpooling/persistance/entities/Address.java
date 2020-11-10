package pl.lodz.p.edu.carpooling.persistance.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@Builder
public class Address {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String city;

    private String street;

    private Long houseNumber;

    @OneToOne(mappedBy = "address")
    private PersonalData personalData;

    @Version
    private Long version;
}
