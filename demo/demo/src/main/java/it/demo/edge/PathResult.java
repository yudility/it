package it.demo.edge;

import it.demo.vertex.InnerVertex;
import it.demo.vertex.Vertex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PathResult {
    Vertex start;
    Vertex end;
    List<Vertex> vertexList;
    List<InnerVertex> innerVertices;
    int minutes; //todo 분단위로 바꿔서 올림 처리
    public PathResult(Vertex start, Vertex end, List<Vertex> vertexList, int minutes) {
        this.start = start;
        this.end = end;
        this.vertexList = vertexList;
        this.minutes = minutes;
    }

}
