package it.demo.edge;

import it.demo.vertex.Vertex;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
//@Builder
//@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="start_point_id")
    private Vertex startVertex;
    //public Long startPointId;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="target_point_id")
    private Vertex targetVertex;
    //public Long targetPointId;

    private float distance;

    private double speed;

    //가중치를 어떻게 할지 생각해보기

    private boolean isUphill;
    private boolean isBus;

    public Edge(Vertex startVertex, Vertex targetVertex, int distance, boolean isUphill) {
        this.startVertex=startVertex;
        this.targetVertex=targetVertex;
        this.distance=distance;
        this.isUphill=isUphill;
    }

}
