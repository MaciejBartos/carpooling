package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate productionDate;

    private String description;

    private Long numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account owner;

    @Version
    private Long version;

}
