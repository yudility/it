package it.demo.service;

import it.demo.edge.CustomWeightEdge;
import it.demo.edge.Edge;
import it.demo.edge.PathResult;
import it.demo.repository.EdgeRepository;
import it.demo.repository.VertexRepository;
import it.demo.vertex.InnerVertex;
import it.demo.vertex.Vertex;
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
public class EdgeBusService {

    private final VertexRepository vertexRepository;
    private final EdgeRepository edgeRepository;

    // parseInnerVertices 함수 추가
    public static List<InnerVertex> parseInnerVertices(String coordinates) {
        List<InnerVertex> innerVerticesList=new ArrayList<>();

        // 좌표값 문자열을 좌표값 쌍으로 분할하여 좌표값을 추출합니다.
        String[] coordinatesArray=coordinates.split( "\\)\\s*,\\s*\\(" ); // 정규식 수정

        // 각 좌표값 쌍을 순회하며 InnerVertex 객체를 생성하여 리스트에 추가합니다.
        for (String coordinatePair : coordinatesArray) {
            // 좌표값 쌍에서 괄호를 제거하고 좌표값을 추출합니다.
            String coordinatePairCleaned=coordinatePair.trim().replaceAll( "[()]", "" );

            // 좌표값 쌍을 쉼표를 기준으로 분할합니다.
            String[] coordinatePairArray=coordinatePairCleaned.split( "," );

            // 좌표값 쌍을 double 형으로 변환하여 InnerVertex 객체를 생성합니다.
            double longitude=0.0;
            double latitude=0.0;

            try {
                if (coordinatePairArray.length >= 2) {
                    longitude=Double.parseDouble( coordinatePairArray[0].trim() );
                    latitude=Double.parseDouble( coordinatePairArray[1].trim() );
                    InnerVertex innerVertex=new InnerVertex( latitude, longitude );
                    innerVerticesList.add( innerVertex );
                } else {
                    // 좌표값이 올바르게 추출되지 않은 경우, 기본값으로 설정합니다.
                    // 혹은 예외를 던지거나 다른 처리를 수행할 수 있습니다.
                    System.err.println( "Invalid coordinate pair: " + coordinatePair );
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // 좌표값 파싱 도중 오류 발생
                System.err.println( "Error parsing coordinate pair: " + coordinatePair );
                e.printStackTrace();
            }
        }
        return innerVerticesList;
    }

    public PathResult findPath(Vertex start, Vertex end) {
        List<Vertex> vertexList=vertexRepository.findAll();
        List<Edge> edgeList=edgeRepository.findAll();

        WeightedMultigraph<Long, CustomWeightEdge> graph=new WeightedMultigraph<>( CustomWeightEdge.class );

        for (Vertex Vertex : vertexList) {
            graph.addVertex( Vertex.getId() );
        }
        for (Edge edge : edgeList) {
            Vertex startVertex=edge.getStartVertex();
            Vertex targetVertex=edge.getTargetVertex();

            if (startVertex != null && targetVertex != null) {
                CustomWeightEdge customWeightEdge=graph.addEdge( startVertex.getId(), targetVertex.getId() );
                customWeightEdge.setId( edge.getId() );
                //customWeightEdge.setBus( edge.isBus() );
//                double time= edge.getDistance() / edge.getSpeed();
                graph.setEdgeWeight( customWeightEdge, edge.getTime() );
                //todo
                //가중치를 어떻게 둘것인가 -> 시간?
                //todo
                //오르막 패널티반영 어떻게 할 것인가
                //Other operations...
            }
        }

        DijkstraShortestPath<Long, CustomWeightEdge> shortestPath=new DijkstraShortestPath<>( graph );

        // 최단 경로 계산
        GraphPath<Long, CustomWeightEdge> path=shortestPath.getPath( start.getId(), end.getId() );

        List<Long> resultVertexList=path.getVertexList();
        List<Vertex> vertices=new ArrayList<>();
        for (Long vertexId : resultVertexList) {
            Optional<Vertex> optionalVertex=vertexRepository.findById( vertexId );
            optionalVertex.ifPresent( vertices::add );
        }
        int minutes=(int) Math.ceil( path.getWeight() );
        PathResult pathResult=new PathResult( start, end, vertices, minutes );

//        List<InnerVertex> innerVertices=new ArrayList<>();
//
//        // 최단 경로에 포함된 간선을 확인하여 isBus가 true인 경우에만 innerVertices를 추가합니다.
//        for (CustomWeightEdge edge : path.getEdgeList()) {
//            if (edge.isBus()) {
//                Optional<Edge> busEdge =edgeRepository.findById( edge.getId() );
//                if( busEdge.isPresent() && !busEdge.get().getInnerVertices().isEmpty()){
//                    List<InnerVertex> innerVertexList = this.parseInnerVertices(busEdge.get().getInnerVertices());
//                    if( !innerVertexList.isEmpty()) innerVertices.addAll( innerVertexList );
//                }
//            }
//        }
//
//        // PathResult 생성
//        PathResult pathResult=new PathResult( start, end, vertices, innerVertices, minutes );
     return pathResult;
   }

}
