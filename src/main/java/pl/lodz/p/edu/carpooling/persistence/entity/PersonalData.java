package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "personal_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalData {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String surname;

    private LocalDate birthDate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy = "personalData", cascade = CascadeType.MERGE)
    private Account account;

    @Version
    private Long version;
}
