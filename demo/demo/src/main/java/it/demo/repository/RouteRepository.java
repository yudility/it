package it.demo.repository;

import it.demo.point.Point;
import it.demo.route.Route;
import jakarta.websocket.PongMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RouteRepository extends JpaRepository<Route, Long> {

    Route save(Route route);
    Optional<Route> findById(Long id);
    Optional<Point> findByStartPointId(Long startPointId);
    Optional<Point> findByTargetPointId(Long TargetPointId);
    List<Route> findAll();


}
