package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "route")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Account host;

    @ManyToMany
    @JoinTable(name = "route_traveler",
            joinColumns = @JoinColumn(name = "routeId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "travelerId", referencedColumnName = "id")
    )
    private List<Account> guests;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
