package it.demo.controller;

import it.demo.point.Point;
import it.demo.repository.PointRepository;
import it.demo.service.RouteService;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PointController extends HttpServlet {

    private final PointRepository pointRepository;
    private final RouteService routeService;

    @GetMapping("hi-api")
    public Point findPointByNameLikeTest(@RequestParam("testing") String testing){

        Point point = new Point();
        point.setId(1L);
        point.setName(testing);
        point.setLatitude(1.5);
        point.setLongitude(2.0);

        return point;
    }
    @GetMapping("save")
    public String dbConnectionTest(@RequestParam("name") String name){

        Point point = new Point();
        //point.setId(1L);
        point.setName(name);
        point.setLatitude(1.5);
        point.setLongitude(2.0);

        pointRepository.save(point);

        return "saved";
    }

    @GetMapping("point/find")
    public ResponseEntity<Object> findPointByName(@RequestParam("name") String name) {
        List<Point> points = pointRepository.findByNameContaining(name);
        if (points == null) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND).body("No points found for the given name");
        }

        List<String> pointNames = new ArrayList<>();
        for (Point point : points) {
            pointNames.add(point.getName());
        }

        return ResponseEntity.ok(pointNames);
    }
//    @GetMapping("find_route")
//    @ResponseBody
//    public Route service(@RequestParam("start") String startName, @RequestParam("destination") String destinationName) {
//
//        Optional<Point> startOptional = pointRepository.findByName(startName);
//        Optional<Point> destinationOptional = pointRepository.findByName(destinationName);
//        //Optional이 아니도록 해야함
//
//        if (startOptional.isPresent() && destinationOptional.isPresent()) {
//            Point start = startOptional.get();
//            Point destination = destinationOptional.get();
//            return new Route(start, destination);
//        } else {
//            //예외처리
//            throw new IllegalArgumentException("Start or destination point not found");
//        }
//
//    }
}
