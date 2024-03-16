package it.demo.route;

import it.demo.point.Point;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="start_point_id")
    private Point startPoint;
    //public Long startPointId;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="target_point_id")
    private Point targetPoint;
    //public Long targetPointId;

    private int times;

    private String vectors; // 배열로 받아야 됨 (HOW?)

    private boolean isUphill;


}
