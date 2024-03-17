package it.demo.repository;

import it.demo.building.Building;
import it.demo.vertex.Vertex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VertexRepository extends JpaRepository<Vertex, Long> {
        Vertex save(Vertex Vertex);
        Optional<Vertex> findById(Long id);
        List<Vertex> findByBuilding(Building building);
        List<Vertex> findAll();

}
