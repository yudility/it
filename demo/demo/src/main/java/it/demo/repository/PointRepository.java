package it.demo.repository;

import it.demo.point.Point;

import java.util.List;
import java.util.Optional;


public interface PointRepository {
        Point save(Point member);
        Optional<Point> findById(Long id);
        List<Point> findByName(String name);
        List<Point> findAll();


}
