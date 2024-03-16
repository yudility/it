package it.demo.controller;

import it.demo.point.Point;
import it.demo.repository.PointRepository;
import it.demo.repository.RouteRepository;
import it.demo.route.PathResult;
import it.demo.route.Route;
import it.demo.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteController {

    private final PointRepository pointRepository;
    //private final RouteRepository routeRepository;
    private final RouteService routeService;

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
    @GetMapping("routh/find")
    public PathResult findShortestPath(@RequestParam("start") String startName, @RequestParam("end") String destinationName){
        Point start=pointRepository.findByName(startName).getFirst();
        Point end=pointRepository.findByName(destinationName).getFirst();

        PathResult pathResult = routeService.findPath( start, end );

        //결과로 나온 edge들을 가지고, start, end로 검색해서 id, 그리기위한 vertex list 구해야함
        //edge DB에서 구한 edge'들'을 weight와 함께 전부 보내줘야함
        return pathResult;

    }


}
