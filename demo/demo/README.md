# 🏫 잇(it)다 - 교내 지름길 찾기 서비스

**이화톤(Ewha-thon) 해커톤 프로젝트** | 2024.03.16 ~ 2024.03.18 (2일)

## 📋 **서비스 개요**
도보 위주의 교내 지름길을 알려주는 서비스입니다. 기존 지도 서비스들이 차량 도로 중심으로 설계되어 실제 도보로 이용할 수 있는 건물 간 연결통로나 지름길을 제대로 반영하지 못하는 문제를 해결하기 위해 제작했습니다.

## 🛠️ **사용기술**
* `Java 21`, `Spring Boot 3.2.3`, `Spring Data JPA`, `MariaDB`, `JGraphT 1.0.1`, `Lombok`

## 🏗️ **시스템 아키텍처**

### 핵심 엔티티 구조
```
Building (건물) ←→ Vertex (위치 노드) ←→ Edge (경로 간선)
                                      ↓
                              InnerVertex (상세 경로)
```

### 주요 컴포넌트
- **EdgeService**: 다익스트라 알고리즘 기반 최단 경로 계산
- **GraphController**: 경로 검색 및 건물 조회 API
- **Repository Layer**: JPA 기반 데이터 접근 계층

## 🔧 **기술적 문제 해결**

**- 2일 내 다익스트라 알고리즘 기반 교내 최적 경로 서비스 완성**

**문제 상황:**
* **단 2일이라는 극도로 제한된 시간** 내 최적 경로 서비스 구현 필요

**해결 과정:**
* **교내 시설을 노드로, 도보 경로를 엣지로 하는 그래프 구조 설계**
* **다익스트라 알고리즘 적용**하여 최적 도보 경로 계산 시스템 구현
* JPA를 활용한 교내 건물, 경로, 연결점 정보 효율적 모델링
* JGraphT 라이브러리의 WeightedMultigraph 활용한 그래프 구현

**핵심 알고리즘:**
```java
// 그래프 구성 및 다익스트라 알고리즘 적용
WeightedMultigraph<Long, CustomWeightEdge> graph = new WeightedMultigraph<>(CustomWeightEdge.class);
DijkstraShortestPath<Long, CustomWeightEdge> shortestPath = new DijkstraShortestPath<>(graph);
GraphPath<Long, CustomWeightEdge> path = shortestPath.getPath(start.getId(), end.getId());
```

**결과:**
* 짧은 기간에도 불구하고 **실제 사용 가능한 수준의 MVP 서비스 완성**
* 시간 기반 가중치를 고려한 최적 경로 제공
* 알고리즘 기반 실용적 솔루션 구현 경험

## 🚀 **주요 기능**

### 1. 최적 경로 찾기
```http
GET /route/find?start={출발지}&end={도착지}
```
- 다익스트라 알고리즘 기반 최단 경로 계산
- 시간 기반 가중치 적용
- 도보 및 버스 경로 통합 고려

### 2. 건물 검색
```http
GET /point/find?name={건물명}
```
- 건물명 부분 검색 지원
- 연관된 모든 Vertex 정보 반환

### 3. 경로 결과 제공
- 출발지/도착지 정보
- 경유 지점 리스트  
- 예상 소요 시간 (분 단위)
- 상세 경로 좌표 (InnerVertex)

## 📊 **데이터 모델링**

### Building (건물)
- 건물 고유 정보 (이름, 설명)

### Vertex (위치 노드)  
- GPS 좌표 (위도, 경도)
- 소속 건물 정보

### Edge (경로 간선)
- 시작점/도착점 Vertex
- 거리, 소요시간, 오르막 여부
- 버스 경로 구분
- 상세 경로 좌표 문자열

## 🎯 **프로젝트 성과**
- **2일 해커톤 완주**: 제한된 시간 내 MVP 서비스 성공적 구현
- **실용적 알고리즘 적용**: 이론적 지식을 실제 서비스에 활용
- **효율적 데이터 모델링**: 복잡한 지리 정보를 관계형 DB로 설계
- **팀 협업 경험**: 다양한 직군과의 협업을 통한 서비스 완성

## 📁 **프로젝트 구조**
```
src/main/java/it/demo/
├── building/          # 건물 엔티티
├── vertex/           # 위치 노드 엔티티  
├── edge/             # 경로 간선 엔티티
├── repository/       # 데이터 접근 계층
├── service/          # 비즈니스 로직 (다익스트라 알고리즘)
├── controller/       # REST API 컨트롤러
└── DemoApplication.java
```

## 🛠️ **실행 방법**
```bash
# 의존성 설치 및 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun
```

**개발환경**: Java 21, MariaDB, Spring Boot 3.2.3

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

- 조회 성공 시
> localhost:8080/route/find?start=정문&end=아산공학관
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
