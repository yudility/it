package it.demo.repository;

import it.demo.point.Point;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PointRepositoryImpl implements PointRepository {

    private final EntityManager em;

    public PointRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Point save(Point point) {
        em.persist(point);
        return point;
    }

    @Override
    public Optional<Point> findById(Long id) {
        Point point = em.find(Point.class, id);
        return Optional.ofNullable(point);
    }

    @Override
    public List<Point> findByName(String name) {
        List<Point> result = em.createQuery("select p from Point p where p.name like concat('%', :name, '%')", Point.class)
                .setParameter("name", name)
                .getResultList();
        return result;
    }

    @Override
    public List<Point> findAll() {
            return em.createQuery("select p from Point p", Point.class)
                    .getResultList();
    }
}
