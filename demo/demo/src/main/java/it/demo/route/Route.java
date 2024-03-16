package it.demo.route;

import it.demo.point.Point;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
//@Builder
//@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    public Route(Point startPoint, Point targetPoint, int times, boolean isUphill) {
        this.startPoint=startPoint;
        this.targetPoint=targetPoint;
        this.times=times;
        this.isUphill=isUphill;
    }

}
