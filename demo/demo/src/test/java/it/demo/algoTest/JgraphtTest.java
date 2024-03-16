package it.demo.algoTest;

import it.demo.point.Point;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.Test;

public class JgraphtTest {

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
    void pointTest(){
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        Point point = new Point();
        for(int i=0; i<3 ; i++){
            point.getName();
        }


        // 정점 추가
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");

        // 간선과 가중치 추가
        DefaultWeightedEdge edge1 = graph.addEdge("1", "2");
        graph.setEdgeWeight(edge1, 1);
        DefaultWeightedEdge edge2 = graph.addEdge("1", "3");
        graph.setEdgeWeight(edge2, 4);
        DefaultWeightedEdge edge3 = graph.addEdge("2", "3");
        graph.setEdgeWeight(edge3, 1);
        DefaultWeightedEdge edge4 = graph.addEdge("2", "4");
        graph.setEdgeWeight(edge4, 5);
        DefaultWeightedEdge edge5 = graph.addEdge("3", "4");
        graph.setEdgeWeight(edge5, 1);

        // 다익스트라 알고리즘을 사용하여 최단 경로 계산
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        String sourceVertex = "1";
        for (String targetVertex : graph.vertexSet()) {
            System.out.println("Shortest path from " + sourceVertex + " to " + targetVertex + ": " +
                    shortestPath.getPath(sourceVertex, targetVertex) + ", weight: " +
                    shortestPath.getPathWeight(sourceVertex, targetVertex));
        }
    }
}
