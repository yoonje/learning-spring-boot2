# Spring Framework 정리 자료
인프런 Spring Boot RESTful Web Services 정리 문서

Table of contents
=================
<!--ts-->
   * [Spring Boot Restful](#Spring-Boot-Restful)
   * [User Service API](#User-Service-API)
   * [RESTful Service](#RESTful-Service)
   * [Spring Boot API](#Spring-Boot-API)
   * [Java Persistence API](#Java-Persistence-API)
   * [RESTful API Design](#RESTful-API-Design)
<!--te-->

Spring Boot Restful
=======
* Spring Boot
  * 단독 실행 가능한 스프링 기반의 애플리케이션을 최소한의 설정으로 개발하기 위해 개발한 플랫폼
  * `@SpringBootApplication` 어노테이션을 갖고 있는 main을 통해서 실행시킬 수 있음
  * `start.spring.io` 웹 사이트를 통해서 스프링 부트 템플릿을 쉽게 만들 수 있고 이를 import 하여 사용 가능
  * IntelliJ의 Spring initializer에서 `start.spring.io`에서와 같이 스프링 부트 프로젝트 생성 가능
* Spring Boot Project
  * Maven을 이용한 Spring Boot 프로젝트 구조
    * src/main/java: 개발할 자바 클래스 파일
    * src/main/resources: static file, html file, 환경 설정 파일
    * src/test: 테스트 코드를 담는 폴더
    * pom.xml: 프로젝트에 필요한 설정 정보를 담긴 파일
  * Maven 명령어
    * Lifecycle/clean: 빌드 및 패키징 시킨 파일을 삭제하는 명령
    * Lifecycle/compile: 작성한 코드를 컴파일하는 명령
    * Lifecycle/package: 컴파일한 내용을 jar/war로 패키징하는 명령
    * Lifecycle/install: 패킹한 내용을 로컬 서버에 배포하는 명령
  * pom.xml 구조
    * <parent>: 스프링 부트 정보 프로젝트에 대한 내용
    * <properties>: 자바 정보에 대한 내용
    * <dependencies>: 스프링 부트 프로젝트 라이브러리 
  * 자바 압축 파일
    * war: jar를 이용한 압축 파일로, 외부의 웹 서버와 동작시키기 위해서 패키징하는 방식
    * jar: jar를 이용한 압축 파일로, 내장된 웹 서버와 동작시키기 위해서 패키징하는 방식
* RESTful API
  * MVC에서 Controller 등록
    * @RestController 어노테이션을 클래스에 등록하고 컨트롤러 클래스 안에 HTTP Method와 연결할 함수 선언하고 `@ResquestMapping(method=RequestMethod.{메소드이름},path="{URL경로}")` 또는 `@{메소드이름}Mapping(path="URL경로")`를 그 함수의 어노테이션으로  설정
    * RestController는 `@Controller + @ResponseBody`로 뷰를 갖지 않는 REST Data를 반환
  * Bean 객체
    * Spring 컨테이너에 관리하는 객체로 스프링의 모든 객체로 Bean 객체를 통해서 클래스를 이용
* lombok 플러그인을 활용하기
  * lombok의 `@Data`: getter, setter, equals, toString을 자동 생성
  * lombok의 `@AllArgsConstructor`: 클래스의 모든 변수를 가진 생성자 자동 생성
  * lombok의 `@NoArgsConstructor`: 클레스의 디폴트 생성자 자동 생성
* DispatcherServlet과 프로젝트 동작
  * application.yml or application.properties를 보고 스프링 부트의 프로젝트를 설정
  * DispathcerServlet -> HandlerMapping -> REST Controller -> HttpResponse
  * DispathcerServlet: 사용자의 요청을 처리해주는 게이트웨이로 클라이언트의 모든 요청을 받아서 처리(`/`)하고 Handler로 요청을 전달
* Path Variable
  * URI에 가변 데이터를 서버가 받기 위해서 Mapping 어노테이션의 인자에 {}를 설정하는 것

User Service API
=======

RESTful Service
=======

Spring Boot API
=======

Java Persistence API
=======

RESTful API Design
=======