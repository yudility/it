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
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
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

        // 그래프 생성
        SimpleDirectedWeightedGraph<Long, CustomWeightEdge> graph=new SimpleDirectedWeightedGraph<>( CustomWeightEdge.class );

        // 먼저 그래프에 모든 점 추가
        for (Vertex Vertex : vertexList) {
            graph.addVertex( Vertex.getId() );
        }

        // DB에 있는 Edge data로 그래프에 엣지 추가
        for (Edge route : edgeList) {
            CustomWeightEdge edge=graph.addEdge( route.getStartVertex().getId(), route.getTargetVertex().getId() );
            edge.setId( route.getId() );
            graph.setEdgeWeight( edge, route.getDistance() );
        }

        DijkstraShortestPath<Long, CustomWeightEdge> shortestPath=new DijkstraShortestPath<>( graph );


        // 최단 경로 계산
        GraphPath<Long, CustomWeightEdge> path = shortestPath.getPath(start.getId(), end.getId());

        List<Long> resultVertexList = path.getVertexList();
        List<Vertex> vertices= new ArrayList<>();
       for (Long vertexId : resultVertexList) {
            Optional<Vertex> optionalVertex = vertexRepository.findById(vertexId);
           optionalVertex.ifPresent( vertices::add);
            // or you can use optionalRoute.ifPresent(route -> routes.add(route));
        }

        PathResult pathResult=new PathResult( start, end, vertices, path.getWeight() );

        return pathResult;

    }
}
