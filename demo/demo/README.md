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


## ERD

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/97d8b7b1-43ee-41b6-b3bc-a7c4b63f041e/11209880-b8b6-48b4-a96a-62b67cee99ae/Untitled.png)

## API

---

## 1. 이름으로 건물 조회

### `point/find?name = “건물이름”`

- 파라미터
    - (String) name : 건물명
- 리턴값
    - Vertex 객체 list

<  조회 성공 시> 

- localhost:8080/point/find?name=tes

```json
[
    {
        "id": 1,
        "latitude": 10.0,
        "longitude": 10.0,
        "building": {
            "id": 1,
            "name": "test\r\n",
            "info": null
        },
        "idAsString": "1"
    },
    {
        "id": 2,
        "latitude": 5.5,
        "longitude": 3.0,
        "building": {
            "id": 2,
            "name": "test1",
            "info": null
        },
        "idAsString": "2"
    },
    {
        "id": 3,
        "latitude": 2.6,
        "longitude": 111.0,
        "building": {
            "id": 3,
            "name": "test2",
            "info": null
        },
        "idAsString": "3"
    }
]
```

조회 실패 시

localhost:8080/point/find?name=ji

```java
[]
```

## 2. 출발지/도착지 이름으로 최단 경로 조회

### `route/find?start = "출발지건물명" & end ="도착지건물명"`

- 파라미터
    - (String) start :  출발지 건물명
    - (String) end : 도착지 건물명
- 리턴값
    - Start 출발지
        - vertex id
        - latitude : 위도
        - longitude : 경도
        - building : 건물
            - id : 건물 ID
            - name : 건물명
            - info : 정보
    - End 도착지
        - 이름
        - 위도
        - 경도
        - 건물
            - 건물 ID
            - 건물명
            - 정보
    - vertexList 좌표(점) List
        - vertex id
        - latitude 위도
        - longitude 경도
        - building 건물
            - id 건물 ID
            - name 건물명
            - info 정보
