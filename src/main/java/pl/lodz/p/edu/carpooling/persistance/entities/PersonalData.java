package pl.lodz.p.edu.carpooling.persistance.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personal_data")
@Data
@Builder
public class PersonalData {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String surname;

    private Long yearsOld;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy = "personalData")
    private Account account;

    @Version
    private Long version;
}
