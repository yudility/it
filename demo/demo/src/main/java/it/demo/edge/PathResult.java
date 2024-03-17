package it.demo.edge;

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
    List<Vertex> VertexList;
    double time;
}
