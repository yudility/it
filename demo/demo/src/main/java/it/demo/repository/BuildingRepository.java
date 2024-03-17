package it.demo.repository;

import it.demo.building.Building;
import it.demo.vertex.Vertex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
        Building save(Building Building);

        List<Building> findByName(String name);

        List<Building> findByNameContaining(String name);

        List<Building> findAll();

}
