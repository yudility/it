package it.demo.service;

import it.demo.repository.BuildingRepository;
import it.demo.vertex.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public List<Vertex> findVerticesByBuildingName(String name) {
        return buildingRepository.findVerticesByBuildingName(name);
    }
}