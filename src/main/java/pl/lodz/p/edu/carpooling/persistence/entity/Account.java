package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String email;

    private String password;

    private boolean active;

    private boolean confirmed;

    private String confirmationToken;

    private LocalDateTime expiryDateOfToken;

    @ManyToMany
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "accountId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(name = "accountId_roleId_unique", columnNames = {"accountId", "roleId"}))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;

    @Version
    private Long version;

}
