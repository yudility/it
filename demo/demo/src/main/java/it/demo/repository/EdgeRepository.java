package it.demo.repository;

import it.demo.vertex.Vertex;
import it.demo.edge.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {

    Edge save(Edge edge);
    Optional<Edge> findById(Long id);

    List<Edge> findAll();


}
