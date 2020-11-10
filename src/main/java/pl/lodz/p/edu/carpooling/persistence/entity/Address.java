package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
