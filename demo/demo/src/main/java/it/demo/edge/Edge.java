package it.demo.edge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.demo.vertex.InnerVertex;
import it.demo.vertex.Vertex;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name="start_vertex_id")
    private Vertex startVertex;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="target_vertex_id")
    private Vertex targetVertex;

    private float distance;

    private double time;

    //가중치를 어떻게 할지 생각해보기

    private boolean isUphill;
    private boolean isBus;
    @Column(name = "inner_vertices", columnDefinition = "TEXT")
    private String innerVertices;
    @Transient
    private List<InnerVertex> innerVerticesList;

    // 생성자, getter 및 setter 등이 있어야 합니다.

/*    @Transient
    public List<InnerVertex> getInnerVerticesList() {
        if (innerVerticesList == null) {
            if (innerVertices != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    innerVerticesList = objectMapper.readValue(innerVertices, new TypeReference<List<InnerVertex>>() {});
                } catch (IOException e) {
                    // 예외 처리
                }
            } else {
                innerVerticesList = new ArrayList<>(); // 빈 리스트 생성 또는 null 대신 다른 기본값 할당
            }
        }
        return innerVerticesList;
    }

    @Transient
    public void setInnerVerticesList(List<InnerVertex> innerVerticesList) {
        this.innerVerticesList = innerVerticesList;
        if (innerVerticesList != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                this.innerVertices = objectMapper.writeValueAsString(innerVerticesList);
            } catch (IOException e) {
                // 예외 처리
            }
        }
    }*/

}
