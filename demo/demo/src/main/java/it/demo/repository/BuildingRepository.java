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
        //Optional<Building> findById(Long id);
        List<Building> findByName(String name);
        String findBuildingNameById(long buildingId);
        List<Building> findByNameLike(String name);
        List<Building> findByNameContaining(String name);

        @Query("SELECT v FROM Vertex v JOIN v.building b WHERE b.name LIKE %:name%")
        List<Vertex> findVerticesByBuildingName(String name);
        List<Building> findAll();

}
