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

    @GetMapping("route/find")
    public ResponseEntity<PathResult> findShortestPathByBuildingName(@RequestParam("start") String startName, @RequestParam("end") String destinationName) {
        Building startBuilding = buildingRepository.findByName(startName).getFirst();
        Building destinationBuilding = buildingRepository.findByName(destinationName).getFirst();

        Vertex start=vertexRepository.findByBuilding( startBuilding ).getFirst();
        Vertex end=vertexRepository.findByBuilding( destinationBuilding ).getFirst();

        PathResult path=edgeService.findPath( start, end );

        if ( path!=null) {
            return ResponseEntity.ok( path );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("routeBus/find")
    public ResponseEntity<PathResult> findShortestPathByBuildingNameWithBus(@RequestParam("start") String startName, @RequestParam("end") String destinationName) {
        Building startBuilding = buildingRepository.findByName(startName).getFirst();
        Building destinationBuilding = buildingRepository.findByName(destinationName).getFirst();

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
