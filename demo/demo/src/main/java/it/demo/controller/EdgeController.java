package it.demo.edge;

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
        //Vertex start=vertexRepository.findByName(startName).getFirst();
        //building 이름으로 찾아서 해당 이름의 building id를 구한다음
        //해당 building id를 가지고 있는 vertex를 찾아서 보내주기
        //Vertex end=vertexRepository.findByName(destinationName).getFirst();

        Vertex start = buildingRepository.findVerticesByBuildingName( startName ).getFirst();
        Vertex end = buildingRepository.findVerticesByBuildingName( destinationName ).getFirst();

        PathResult pathResult = edgeService.findPath( start, end );

        //결과로 나온 edge들을 가지고, start, end로 검색해서 id, 그리기위한 vertex list 구해야함
        //edge DB에서 구한 edge'들'을 weight와 함께 전부 보내줘야함
        return pathResult;

    }


}
