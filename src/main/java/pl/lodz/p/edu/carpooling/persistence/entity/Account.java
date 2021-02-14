package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Column(unique=true)
    private String login;

    @Column(unique=true)
    private String email;

    private String password;

    private boolean active;

    private boolean confirmed;

    private String confirmationEmailToken;

    private String resetPasswordEmailToken;

    private LocalDateTime expiryDateOfEmailToken;

    @ManyToMany
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "accountId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "host")
    private List<Direction> createdDirections = new ArrayList<>();

    @ManyToMany(mappedBy = "guests")
    private List<Direction> directionsAssignedTo = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;

    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return active == account.active &&
                confirmed == account.confirmed &&
                id.equals(account.id) &&
                login.equals(account.login) &&
                email.equals(account.email) &&
                password.equals(account.password) &&
                Objects.equals(confirmationEmailToken, account.confirmationEmailToken) &&
                Objects.equals(resetPasswordEmailToken, account.resetPasswordEmailToken) &&
                Objects.equals(expiryDateOfEmailToken, account.expiryDateOfEmailToken) &&
                version.equals(account.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password, active, confirmed, confirmationEmailToken, resetPasswordEmailToken, expiryDateOfEmailToken, version);
    }
}
