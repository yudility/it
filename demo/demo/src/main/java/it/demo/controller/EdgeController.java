package it.demo.controller;

import it.demo.edge.PathResult;
import it.demo.repository.BuildingRepository;
import it.demo.vertex.Vertex;
import it.demo.repository.VertexRepository;
import it.demo.service.EdgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("edge/find")
    public PathResult findShortestPath(@RequestParam("start") String startName, @RequestParam("end") String destinationName){

        Vertex start = buildingRepository.findVerticesByBuildingName( startName ).getFirst();
        Vertex end = buildingRepository.findVerticesByBuildingName( destinationName ).getFirst();

        PathResult pathResult = edgeService.findPath( start, end );

        return pathResult;

    }


}
