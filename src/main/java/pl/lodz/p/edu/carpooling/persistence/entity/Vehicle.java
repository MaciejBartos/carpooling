package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    private String model;

    private Long carProductionDate;

    private String description;

    private Long seats;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account owner;

    @Version
    private Long version;

}
