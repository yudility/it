package it.demo.controller;

import it.demo.building.Building;
import it.demo.repository.BuildingRepository;
import it.demo.repository.VertexRepository;
import it.demo.service.EdgeService;
import it.demo.vertex.Vertex;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class VertexController extends HttpServlet {

    private final VertexRepository vertexRepository;
    private final BuildingRepository buildingRepository;
    private final EdgeService edgeService;

    @GetMapping("point/find")
    public ResponseEntity<Object> findPointByName(@RequestParam("name") String name) {
        List<Building> buildingList=buildingRepository.findByNameContaining( name );
        if (buildingList == null) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "No points found for the given name" );
        }
        else {
            List<Vertex> result=new ArrayList<>();
            for (Building building : buildingList) {
                List<Vertex> vertices= vertexRepository.findByBuilding( building );
                if (vertices != null)
                    result.addAll( vertices );
            }
            return ResponseEntity.ok( result );
        }


    }
}

