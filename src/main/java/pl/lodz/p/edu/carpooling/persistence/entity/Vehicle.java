package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    private String brand;

    private String model;

    private Long productionYear;

    private String description;

    private Integer numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account owner;

    @Version
    private Long version;

}
