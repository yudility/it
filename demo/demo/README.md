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


## ER Diagram

<img width="593" alt="image" src="https://github.com/yudility/it/assets/78692557/130f5a39-586b-4fde-b0a2-a8689338fbda">



## API
### 1. 이름으로 건물 조회

### `point/find?name = “건물이름”`

- 파라미터
> - (String) name : 건물명
- 리턴값
> - Vertex 객체 list

- 조회 성공 시
>   localhost:8080/point/find?name=tes

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

- 조회 실패 시
> localhost:8080/point/find?name=ji

```java
[]
```

## 2. 출발지/도착지 이름으로 최단 경로 조회

### `route/find?start = "출발지건물명" & end ="도착지건물명"`

- 파라미터
>    - (String) start :  출발지 건물명
>    - (String) end : 도착지 건물명
- 리턴값
>    - Start 출발지
>        - vertex id
>        - latitude : 위도
>        - longitude : 경도
>        - building : 건물
>            - id : 건물 ID
>            - name : 건물명
>            - info : 정보
>    - End 도착지
>        - 이름
>        - 위도
>        - 경도
>        - 건물
>            - 건물 ID
>            - 건물명
>            - 정보
>    - vertexList 좌표(점) List
>        - vertex id
>        - latitude 위도
>        - longitude 경도
>        - building 건물
>            - id 건물 ID
>            - name 건물명
>            - info 정보

```json
{
    "start": {
        "id": 1,
        "latitude": 37.55926864600008,
        "longitude": 126.9457978910001,
        "building": {
            "id": 1,
            "name": "정문",
            "info": null
        }
    },
    "end": {
        "id": 146,
        "latitude": 37.56645861900006,
        "longitude": 126.94844940400003,
        "building": {
            "id": 62,
            "name": "아산공학관",
            "info": null
        }
    },
    "vertexList": [
        {
            "id": 1,
            "latitude": 37.55926864600008,
            "longitude": 126.9457978910001,
            "building": {
                "id": 1,
                "name": "정문",
                "info": null
            }
        },
        {
            "id": 2,
            "latitude": 37.56001399500008,
            "longitude": 126.94567690500003,
            "building": null
        },
        {
            "id": 84,
            "latitude": 37.561321933000045,
            "longitude": 126.94679141100005,
            "building": null
        },
        {
            "id": 85,
            "latitude": 37.56169943800006,
            "longitude": 126.94729475100007,
            "building": null
        },
        {
            "id": 87,
            "latitude": 37.561901404000025,
            "longitude": 126.94743096700006,
            "building": null
        },
        {
            "id": 86,
            "latitude": 37.562160608000056,
            "longitude": 126.94700962400009,
            "building": null
        },
        {
            "id": 55,
            "latitude": 37.56233325900007,
            "longitude": 126.94714351400012,
            "building": null
        },
        {
            "id": 54,
            "latitude": 37.56289048100007,
            "longitude": 126.94735530800006,
            "building": null
        },
        {
            "id": 53,
            "latitude": 37.56311035900006,
            "longitude": 126.94730913300009,
            "building": null
        },
        {
            "id": 56,
            "latitude": 37.563318897000045,
            "longitude": 126.94783952700004,
            "building": null
        },
        {
            "id": 57,
            "latitude": 37.56364550700005,
            "longitude": 126.94814407000001,
            "building": null
        },
        {
            "id": 58,
            "latitude": 37.563813226000036,
            "longitude": 126.94800724600009,
            "building": null
        },
        {
            "id": 59,
            "latitude": 37.56391032700003,
            "longitude": 126.94794545500008,
            "building": null
        },
        {
            "id": 115,
            "latitude": 37.563993793000066,
            "longitude": 126.94785120100005,
            "building": {
                "id": 58,
                "name": "종합과학관 A동",
                "info": null
            }
        },
        {
            "id": 114,
            "latitude": 37.564334305000045,
            "longitude": 126.94746116100009,
            "building": {
                "id": 59,
                "name": "종합과학관 B동",
                "info": null
            }
        },
        {
            "id": 141,
            "latitude": 37.56427751000007,
            "longitude": 126.94780991800008,
            "building": null
        },
        {
            "id": 150,
            "latitude": 37.56467861600004,
            "longitude": 126.94809438700008,
            "building": null
        },
        {
            "id": 148,
            "latitude": 37.56489176100007,
            "longitude": 126.94797305500003,
            "building": null
        },
        {
            "id": 147,
            "latitude": 37.56511690100007,
            "longitude": 126.94801810900003,
            "building": null
        },
        {
            "id": 145,
            "latitude": 37.56629442600007,
            "longitude": 126.94875809000007,
            "building": null
        },
        {
            "id": 146,
            "latitude": 37.56645861900006,
            "longitude": 126.94844940400003,
            "building": {
                "id": 62,
                "name": "아산공학관",
                "info": null
            }
        }
    ],
    "innerVertices": null,
    "minutes": 12
}
```
