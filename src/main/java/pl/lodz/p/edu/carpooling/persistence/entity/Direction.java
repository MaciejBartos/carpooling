package pl.lodz.p.edu.carpooling.persistence.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "direction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
})
@Builder
public class Direction {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Account host;

    @ManyToMany
    @JoinTable(name = "direction_traveler",
            joinColumns = @JoinColumn(name = "direction_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "traveler_id", referencedColumnName = "id")
    )
    private List<Account> guests;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "origin_id")
    private Coordinate origin;

    private String originAddress;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "destination_id")
    private Coordinate destination;

    private String destinationAddress;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "direction_waypoints",
            joinColumns = @JoinColumn(name = "direction_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coordinate_id", referencedColumnName = "id")
    )
    private List<Coordinate> waypoint;

    private LocalDateTime travelDate;

    private boolean active;

    private Integer numberOfAvailableSeats;

    @Version
    private Long version;

}
