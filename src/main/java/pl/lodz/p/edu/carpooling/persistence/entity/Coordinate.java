package pl.lodz.p.edu.carpooling.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "coordinate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {

    @Id
    @GeneratedValue
    private Long id;

    private Double latitude;

    private Double longitude;

    @Version
    private Long version;
}
