package it.demo.algoTest;

import it.demo.point.Point;
import it.demo.repository.PointRepository;
import it.demo.repository.RouteRepository;
import it.demo.route.CustomWeightEdge;
import it.demo.route.Route;
import lombok.RequiredArgsConstructor;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
public class JgraphtTest {

    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Test
    void testJ(){
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        // 정점 추가
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // 간선과 가중치 추가
        DefaultWeightedEdge edge1 = graph.addEdge("A", "B");
        graph.setEdgeWeight(edge1, 1);
        DefaultWeightedEdge edge2 = graph.addEdge("A", "C");
        graph.setEdgeWeight(edge2, 4);
        DefaultWeightedEdge edge3 = graph.addEdge("B", "C");
        graph.setEdgeWeight(edge3, 2);
        DefaultWeightedEdge edge4 = graph.addEdge("B", "D");
        graph.setEdgeWeight(edge4, 5);
        DefaultWeightedEdge edge5 = graph.addEdge("C", "D");
        graph.setEdgeWeight(edge5, 1);

        // 다익스트라 알고리즘을 사용하여 최단 경로 계산
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        String sourceVertex = "A";
        for (String targetVertex : graph.vertexSet()) {
            System.out.println("Shortest path from " + sourceVertex + " to " + targetVertex + ": " +
                    shortestPath.getPath(sourceVertex, targetVertex) + ", weight: " +
                    shortestPath.getPathWeight(sourceVertex, targetVertex));
        }
    }


    @Test
    void pointListTest(){
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        List<Point> pointList= new ArrayList<>();

        Point point1=new Point("1" );
        Point point2=new Point("2" );
        Point point3=new Point("3" );


        graph.addVertex( point1.getName() );
        graph.addVertex( point2.getName() );
        graph.addVertex( point3.getName() );


        Route route1=new Route( point1, point2, 1, false );
        Route route2=new Route( point1, point3, 3, false );
        Route route3=new Route( point2, point3, 1, false );

        DefaultWeightedEdge edge1 = graph.addEdge(route1.getStartPoint().getName(), route1.getTargetPoint().getName());
        graph.setEdgeWeight(edge1, route1.getTimes());
        DefaultWeightedEdge edge2 = graph.addEdge(route2.getStartPoint().getName(), route2.getTargetPoint().getName());
        graph.setEdgeWeight(edge2, route2.getTimes());
        DefaultWeightedEdge edge3 = graph.addEdge(route3.getStartPoint().getName(), route3.getTargetPoint().getName());
        graph.setEdgeWeight(edge3, route3.getTimes());

        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        String sourceVertex = point1.getName();
        for (String targetVertex : graph.vertexSet()) {
            System.out.println("Shortest path from " + sourceVertex + " to " + targetVertex + ": " +
                    shortestPath.getPath(sourceVertex, targetVertex) + ", weight: " +
                    shortestPath.getPathWeight(sourceVertex, targetVertex));
        }

    }


    @Test
    void pointListRepoTest() {
        // Point와 Route를 불러올 Repository 선언
        List<Point> pointList = pointRepository.findAll();
        List<Route> routeList = routeRepository.findAll();

        // 그래프 생성
        SimpleDirectedWeightedGraph<String, CustomWeightEdge> graph = new SimpleDirectedWeightedGraph<>(CustomWeightEdge.class);

        // 먼저 그래프에 모든 점 추가
        for (Point point : pointList) {
            graph.addVertex(point.getName());
        }

        // 각 Route를 이용하여 그래프에 엣지 추가
        for (Route route : routeList) {
            CustomWeightEdge edge =(CustomWeightEdge) graph.addEdge(route.getStartPoint().getName(), route.getTargetPoint().getName());
            edge.setId(route.getId());
            graph.setEdgeWeight(edge, route.getTimes());
        }

        // 최단 경로 계산
        DijkstraShortestPath<String, CustomWeightEdge> shortestPath = new DijkstraShortestPath<>(graph);


        //Scanner scanner = new Scanner(System.in);
        System.out.print("Enter source vertex name: ");
        String sourceVertexName="test";
        System.out.print("Enter target vertex name: ");
        String targetVertexName="test2";

        // 입력받은 sourceVertex와 targetVertex를 이용하여 최단 경로 계산
//        System.out.println("Shortest path from " + sourceVertexName + " to " + targetVertexName + ": " +
//                shortestPath.getPath(sourceVertexName, targetVertexName) + ", weight: " +
//                shortestPath.getPathWeight(sourceVertexName, targetVertexName));
        GraphPath<String, CustomWeightEdge> path=shortestPath.getPath( sourceVertexName, targetVertexName );
        System.out.println( "path.getEdgeList() = " + path.getEdgeList() );
        System.out.println( "path.getVertexList() = " + path.getVertexList() );
        System.out.println( "path.toString() = " + path.toString() );
        List<CustomWeightEdge> edges= path.getEdgeList();

        for(CustomWeightEdge edge : edges){
            System.out.println( "edge = " + edge.getId() );
        }

    }

/*
    @Test
    void pointTest(){
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        List<Point> pointList=pointRepository.findAll();
        List<Route> routeList=routeRepository.findAll();

        // 정점 추가
        for(Point point: pointList){
            graph.addVertex(point.getName());
        }

        // 간선과 가중치 추가
        for (int i = 0; i < routeList.size(); i++) {
            Route route = routeList.get(i);
            String start=route.getStartPoint().getName();
            String target = route.getTargetPoint().getName();
            DefaultWeightedEdge edge = graph.addEdge(start, target);
            graph.setEdgeWeight(edge, route.getTimes());
        }


        // 다익스트라 알고리즘을 사용하여 최단 경로 계산
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        String sourceVertex = "1";
        for (String targetVertex : graph.vertexSet()) {
            System.out.println("Shortest path from " + sourceVertex + " to " + targetVertex + ": " +
                    shortestPath.getPath(sourceVertex, targetVertex) + ", weight: " +
                    shortestPath.getPathWeight(sourceVertex, targetVertex));
        }
    }*/
}
