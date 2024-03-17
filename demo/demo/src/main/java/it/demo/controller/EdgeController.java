package it.demo.controller;

import it.demo.building.Building;
import it.demo.edge.PathResult;
import it.demo.repository.BuildingRepository;
import it.demo.vertex.Vertex;
import it.demo.repository.VertexRepository;
import it.demo.service.EdgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class EdgeController {

    private final VertexRepository vertexRepository;
    private final BuildingRepository buildingRepository;

    private final EdgeService edgeService;

/*    @GetMapping("route")
    public Route findRouteByIdTest(@RequestParam("id") Long id){
        Point point1=(Point) pointRepository.findByName("test");
        Point point2=(Point) pointRepository.findByName("test1");

        Route route = new Route();
        route.setStartPoint( point1 );
        route.setStartPoint( point2 );

        route.setTimes(5);

        return route;
    }*/
/*    @GetMapping("edge/find")
    public PathResult findShortestPath(@RequestParam("start") String startName, @RequestParam("end") String destinationName){

        Optional<Vertex> start =Optional.ofNullable( buildingRepository.findVerticesByBuildingName( startName ).getFirst() );
        Optional<Vertex> end = Optional.ofNullable( buildingRepository.findVerticesByBuildingName( destinationName ).getFirst() );

        if(start.isPresent() && end.isPresent()){
            PathResult pathResult = edgeService.findPath( start, end );

            return pathResult;
        }
        else {
            예외처리
        }

    }*/
/* ee
    @GetMapping("route/find")
    public ResponseEntity<PathResult> findShortestPath(@RequestParam("start") String startName, @RequestParam("end") String destinationName) {
        List<Vertex> startVertices=buildingRepository.findVerticesByBuildingName( startName );
        List<Vertex> endVertices=buildingRepository.findVerticesByBuildingName( destinationName );

        if ( !startVertices.isEmpty() && !endVertices.isEmpty()) {
            Vertex start=startVertices.getFirst();
            Vertex end=endVertices.getFirst();

            PathResult pathResult=edgeService.findPath( start, end );
            return ResponseEntity.ok( pathResult );

        } else {
            return ResponseEntity.notFound().build();
        }
    }
*/

    @GetMapping("route/find")
    public ResponseEntity<PathResult> findShortestPathByBuildingName(@RequestParam("start") String startName, @RequestParam("end") String destinationName) {
        Building startBuilding = buildingRepository.findByName( startName).getFirst();
        Building destinationBuilding = buildingRepository.findByName( destinationName ).getFirst();

        Vertex start=vertexRepository.findByBuilding( startBuilding ).getFirst();
        Vertex end=vertexRepository.findByBuilding( destinationBuilding ).getFirst();

        PathResult path=edgeService.findPath( start, end );

        if ( path!=null) {
            return ResponseEntity.ok( path );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
