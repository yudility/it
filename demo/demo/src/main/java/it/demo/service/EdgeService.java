package it.demo.service;

import it.demo.vertex.Vertex;
import it.demo.repository.VertexRepository;
import it.demo.repository.EdgeRepository;
import it.demo.edge.CustomWeightEdge;
import it.demo.edge.PathResult;
import it.demo.edge.Edge;

import lombok.RequiredArgsConstructor;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.WeightedMultigraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EdgeService {

    private final VertexRepository vertexRepository;
    private final EdgeRepository edgeRepository;

    public PathResult findPath(Vertex start, Vertex end) {
        List<Vertex> vertexList=vertexRepository.findAll();
        List<Edge> edgeList=edgeRepository.findAll();

        WeightedMultigraph<Long, CustomWeightEdge> graph=new WeightedMultigraph<>( CustomWeightEdge.class );

        for (Vertex Vertex : vertexList) {
            graph.addVertex( Vertex.getId() );
        }
        for (Edge edge : edgeList) {
            Vertex startVertex = edge.getStartVertex();
            Vertex targetVertex =edge.getTargetVertex();

            if (startVertex != null && targetVertex != null) {
                CustomWeightEdge customWeightEdge = graph.addEdge( startVertex.getId(), targetVertex.getId() );
                customWeightEdge.setId( edge.getId() );
//                double time= edge.getDistance() / edge.getSpeed();
                graph.setEdgeWeight( customWeightEdge, edge.getDistance() );
                //todo
                //가중치를 어떻게 둘것인가 -> 시간?
                //todo
                //오르막 패널티반영 어떻게 할 것인가
                // Other operations...
            }
        }

        DijkstraShortestPath<Long, CustomWeightEdge> shortestPath=new DijkstraShortestPath<>( graph );

        // 최단 경로 계산
        GraphPath<Long, CustomWeightEdge> path = shortestPath.getPath(start.getId(), end.getId());

        List<Long> resultVertexList = path.getVertexList();
        List<Vertex> vertices= new ArrayList<>();
       for (Long vertexId : resultVertexList) {
            Optional<Vertex> optionalVertex = vertexRepository.findById(vertexId);
            optionalVertex.ifPresent( vertices::add);
        }

        PathResult pathResult=new PathResult( start, end, vertices, path.getWeight() );

        return pathResult;

    }
}
