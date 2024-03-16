package it.demo.point;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
//@Data
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double latitude;
    private double longitude;

    public Point(String name) {
        this.name=name;
    }
}
