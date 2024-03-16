package it.demo.vertex;


import it.demo.building.Building;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vertex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double latitude;
    private double longitude;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name ="building_id")
    private Building building;

    public String getIdAsString() {
        return String.valueOf(id);
    }

}
