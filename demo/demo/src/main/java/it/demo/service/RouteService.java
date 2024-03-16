package it.demo.service;

import it.demo.point.Point;
import it.demo.repository.PointRepository;
import it.demo.repository.RouteRepository;
import it.demo.route.CustomWeightEdge;
import it.demo.route.PathResult;
import it.demo.route.Route;
import lombok.RequiredArgsConstructor;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final PointRepository pointRepository;
    private final RouteRepository routeRepository;

    public PathResult findPath(Point start, Point end) {
        List<Point> pointList=pointRepository.findAll();
        List<Route> routeList=routeRepository.findAll();

        // 그래프 생성
        SimpleDirectedWeightedGraph<String, CustomWeightEdge> graph=new SimpleDirectedWeightedGraph<>( CustomWeightEdge.class );

        // 먼저 그래프에 모든 점 추가
        for (Point point : pointList) {
            graph.addVertex( point.getName() );
        }

        // 각 Route를 이용하여 그래프에 엣지 추가
        for (Route route : routeList) {
            CustomWeightEdge edge=graph.addEdge( route.getStartPoint().getName(), route.getTargetPoint().getName() );
            edge.setId( route.getId() );
            graph.setEdgeWeight( edge, route.getTimes() );
        }


        DijkstraShortestPath<String, CustomWeightEdge> shortestPath=new DijkstraShortestPath<>( graph );


        String sourceVertexName=start.getName();
        String targetVertexName=end.getName();

        // 최단 경로 계산
        GraphPath<String, CustomWeightEdge> path = shortestPath.getPath(sourceVertexName, targetVertexName);

        List<CustomWeightEdge> edgeList = path.getEdgeList();
        List<Route> routes = new ArrayList<>();
        for (CustomWeightEdge edge : edgeList) {
            Optional<Route> optionalRoute = routeRepository.findById(edge.getId());
            optionalRoute.ifPresent(routes::add);
            // or you can use optionalRoute.ifPresent(route -> routes.add(route));
        }

        PathResult pathResult=new PathResult( routes, path.getWeight() );

        return pathResult;

    }
}
