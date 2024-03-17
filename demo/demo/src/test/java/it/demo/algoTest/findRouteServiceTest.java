package it.demo.algoTest;


import it.demo.building.Building;
import it.demo.edge.PathResult;
import it.demo.repository.BuildingRepository;
import it.demo.repository.EdgeRepository;
import it.demo.repository.VertexRepository;
import it.demo.service.EdgeService;
import it.demo.vertex.Vertex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class findRouteServiceTest {

    @Autowired
    private  VertexRepository vertexRepository;
    @Autowired
    private  EdgeRepository edgeRepository;
    @Autowired
    private  BuildingRepository buildingRepository;
    @Autowired
    private  EdgeService edgeService;


    @Test
    @DisplayName( "정문 -> 음악관 경로 찾기" )
    void findRoute(){

        Building startBuilding =buildingRepository.findByName( "정문" ).getFirst();
        Building destinationBuilding =buildingRepository.findByName( "음악관" ).getFirst();

        Vertex start=vertexRepository.findByBuilding( startBuilding ).getFirst();
        Vertex end=vertexRepository.findByBuilding( destinationBuilding ).getFirst();

        PathResult path=edgeService.findPath( start, end );

        System.out.println( "start = " + path.getStart());
        System.out.println( "path = " + path.getEnd() );
        System.out.println( "path = " + path.getTime() );
        System.out.println( "path.getVertexList() = " + path.getVertexList() );

    }

}
