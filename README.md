# Place-Search

---------------------------
카카오 네이버 Open API를 통해 지역을 검색하는 프로젝트입니다.
    
* Spring Boot, Gradle ,h2

## API 명세(Swagger)
``` C
http://localhost:8080/swagger-ui/index.html
```
---------------------------

## API 내용

---------------------------

### 장소 검색
``` C
http://localhost:8080/v1.0/places?query=%EA%B9%80%EB%B0%A5
curl -X GET "http://localhost:8080/v1.0/places?query=%EA%B9%80%EB%B0%A5" -H "accept: application/json;charset=UTF-8"
```
 * query : 지역 검색 키워드

#### 장소 검색 성공
``` C
{"header":{"resultCode":200,"resultMessage":"OK"},"body":[{"name":"늘봄흑돼지"},{"name":"돝고기506"},{"name":"육화식당"},{"name":"은주정"},{"name":"두껍삼 역삼직영점"},{"name":"금돼지식당"},{"name":"숙성도 노형본관"},{"name":"몽탄"},{"name":"창심관"},{"name":"동두천솥뚜껑삼겹살 합정점"}]}
```

#### 장소 검색 실패
``` C
{"header":{"resultCode":500,"resultMessage":"Internal Server Error"},"body":null}
```

### 검색 키워드 목록
``` C
http://localhost:8080/v1.0/places/search/rank/lists
curl -X GET "http://localhost:8080/v1.0/places/search/rank/lists" -H "accept: application/json;charset=UTF-8"
```
#### 검색 키워드 목록 성공
``` C
{"header":{"resultCode":200,"resultMessage":"OK"},"body":[{"query":"삼겹살","count":9},{"query":"교촌치킨","count":9},{"query":"곱창","count":9},{"query":"양곱창","count":7},{"query":"치킨","count":6},{"query":"감자탕","count":6}]}
```

------------------

## DB
``` C
spring:
  datasource:
    url: jdbc:h2:mem:placeSearch
    driver-class-name: org.h2.Driver
    username: sa
    password: sa
```

------------------


