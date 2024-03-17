## Backend 파일 구조
```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂it
 ┃ ┃ ┃ ┗ 📂demo
 ┃ ┃ ┃ ┃ ┣ 📂building
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Building.java
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EdgeController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜VertexController.java
 ┃ ┃ ┃ ┃ ┣ 📂edge
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomWeightEdge.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Edge.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PathResult.java 
 ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📜BuildingRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EdgeRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜VertexRepository.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EdgeBusService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜EdgeService.java
 ┃ ┃ ┃ ┃ ┣ 📂vertex
 ┃ ┃ ┃ ┃ ┃ ┣ 📜InnerVertex.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Vertex.java
 ┃ ┃ ┃ ┃ ┣ 📜DemoApplication.java
 ┃ ┃ ┃ ┃ ┗ 📜ServletInitializer.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂it
 ┃ ┃ ┃ ┗ 📂demo
 ┃ ┃ ┃ ┃ ┣ 📂algoTest
 ┃ ┃ ┃ ┃ ┃ ┗ 📜findRouteServiceTest.java
 ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┗ 📜DemoApplicationTests.java
```
