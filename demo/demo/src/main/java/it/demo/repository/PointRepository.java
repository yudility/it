package it.demo.repository;

import it.demo.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
        Point save(Point point);
        Optional<Point> findById(Long id);
        List<Point> findByName(String name);
        List<Point> findByNameLike(String name);

        List<Point> findByNameContaining(String name);
        List<Point> findAll();

}
