# Spring Framework 정리 자료
Spring Boot RESTful Web Services 정리 문서

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
    * <parent> : 스프링 부트 정보 프로젝트에 대한 내용
    * <properties> : 자바 정보에 대한 내용
    * <dependencies> : 스프링 부트 프로젝트 라이브러리 
  * 자바 압축 파일
    * war: jar를 이용한 압축 파일로, 외부의 웹 서버와 동작시키기 위해서 패키징하는 방식
    * jar: jar를 이용한 압축 파일로, 내장된 웹 서버와 동작시키기 위해서 패키징하는 방식
* RESTful API
  * MVC에서 Controller 등록
    * @RestController 어노테이션을 클래스에 등록하고 컨트롤러 클래스 안에 HTTP Method와 연결할 함수 선언하고 `@ResquestMapping(method=RequestMethod.{메소드이름},path="{URL경로}")` 또는 `@{메소드이름}Mapping(path="URL경로")`를 그 함수의 어노테이션으로  설정
    * `@RequestMapping(path="{URL경로}")`는 클래스에 등록하면 해당 클래스의 디폴트 경로가 연결될 수 있음
    * RestController는 `@Controller + @ResponseBody`로 뷰를 갖지 않는 REST Data를 반환
  * Bean 객체
    * Spring 컨테이너에 관리하는 객체로 스프링의 모든 객체는 Bean 객체로 의존성 주입이라는 형태로 관리
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
  * 핸들러 함수 전달인자에서 `@PathVariable` 어노테이션을 통해 값을 받을 수 있고 전달인자의 자료형을 지정하여 String을 형변환 시킬 수 있음

User Service API
=======
* 프로젝트 구조
  * User: 다룰 데이터 모델 클래스
  * UserDaoService: 비즈니스 로직에 연관된 기능에 대한 클래스 
  * UserController: 서비스 인스턴스를 스프링에 의해 관리되도록 의존성 주입을 통해 서비스를 사용할 MVC의 컨트롤러
* GET 요청 처리
  * `@Service` 어노테이션을 통해서 stereotype을 설정하여 서비스 클래스를 설정
  * `@RestController` 어노테이션을 통해서 컨트롤러 클래스를 설정하고 `@GetMapping` 어노테이션과 url matching 함수를 통해서 핸들러 함수 정의 
* POST 요청 처리
  * `@Service` 어노테이션을 통해서 stereotype을 설정하여 서비스 클래스를 설정
  * `@RestController` 어노테이션을 통해서 컨트롤러 클래스를 설정하고 `@PostMapping` 어노테이션과 url matching 함수를 통해서 핸들러 함수 정의
  * 사용자의 Request의 Body 안에 있는 데이터를 받기 위헤서 `@RequestBody` 어노테이션과 전달인자를 핸들러 함수에 정의
* HTTP 상태 리턴 변경
  * `ServletUriComponentBuilder`를 통해서 HTTP 상태 코드, path를 변경할 수 있음
  * 핸들러 함수에서 URI를 생성하고 이에 대해서 ResponseEntity를 만들어서 리턴하게 하여 처리
* HTTP 상태 코드 변경
  * 서버가 HTTP 요청을 처리할 수 없을 경우에 사용할 예외 클래스를 만들고 `@ResponseStatus` 어노테이션을 통해서 예외를 던져 처리할 수 있음
  * `@RestContoroller` 어노테이션을 추가한 예외 핸들러 클래스를 만들고 `@ControllerAdvice` 어노테이션을 추가하여 모든 컨트롤러가 실행될 때 사전 실행될 수 있도록 AOP를 활용하여 추가
  * `@RestContoroller`와 `@ControllerAdvice` 어노테이션이 추가되고 `ResponseEntityExceptionHanlder`를 상속받은 예외 핸들러 클래스는 다른 컨트롤러가 실행될 때 예외가 나면 모든 예외 상황에서 해당 핸들러 함수로 처리가 되도록 설정됨 
  * `@ExceptionHandler` 어노테이션을 추가하여 어떤 예외를 처리할지를 구체적으로 지정 가능
* DELETE 요청 처리
  * `@Service` 어노테이션을 통해서 stereotype을 설정하여 서비스 클래스를 설정
  * `@RestController` 어노테이션을 통해서 컨트롤러 클래스를 설정하고 `@DeleteMapping` 어노테이션과 url matching 함수를 통해서 핸들러 함수 정의

RESTful Service
=======
* Validation
  * javax의 validation이나 하이버네이트 validation API를 통해서 유효성 검증을 할 수 있음
  * `@Size` 어노테이션을 통해서 사용자 입력에 대한 크기를 지정할 수 있고 `@Past` 어노테이션을 과거 값만 입력 받을 수 있게 지정할 수 있음
  * `@Valid` 어노테이션을 `@RequestBody` 어노테이션과 함께 HTTP 요청 핸들러 함수의 파라미터에 지정하여 바디의 유효성을 검증
* Filtering
  * 방법 1
    * 특정 필드를 외부에 노출시키고 싶지 않을 때 `@JsonIgnore` 어노테이션을 클래스 단위로 설정하거나 필드 위에 추가하여 외부에 노출을 막아줌
  * 방법 2
    * `@JsonFilter`에서 필터 이름을 정의하고 API 핸들러 함수 안에서 `SimpleBeanPropertyFilter` 객체를 선언하고 객체의 `filterOutAllExcept` 함수를 사용하여 필터링 하지 않을 필드를 선택
    * `FilterProvider` 객체를 `SimpleFilterProvider`에 addFilter하여 선언하고 이후 `FilterProvider` 객체를 `MappingJacksonVlaue`에 필터로 설정하고 `MappingJacksonVlaue`에 필터링에 대상이되는 Bean 객체를 지정한 다음 `MappingJacksonVlaue` 객체를 리턴하면 properties를 필터할 수 있음
* Versioning
  * 방법 1 : url에 버전 정보를 넣어서 각각의 버전 별로 분리하여 API를 제공
    * 이전 버전에 대한 모델과 컨트롤러를 통해서 다음 버전을 쉽게 개발
    * `BeanUtils`를 통해서 각각의 빈간의 데이터를 쉽게 복사할 수 있음
  * 방법 2 : 요청 파라미터를 넘겨줘서 버전 별로를 분리하여 API를 제공
  * 방법 3 : 헤더 정보를 이용해서 버전 별로를 분리하여 API를 제공
   
Spring Boot API
=======
* HETEOAS
  * REST API에서 현재 리소스와 연관된 자원 상태의 추가적인 정보를 제공하는 기능
  * 2.1 버전이라면 `Resource` / `ControllerLinkBuilder`를 사용하여 이용 가능
  * `ControllerLinkBuilder`를 통해서 연관된 API를 링킹
* Swagger
  * REST API에 대한 문서를 추가할 수 있는 기능
  * `@Configuration` 어노테이션을 통해 설정이라는 것을 명시하고 `@EnableSwagger2` 어노테이션을 통해서 스웨거 생성 설정
  * `@Bean`으로 등록한 메소드에서 `DocumentationType.SWAGGER_2`로 스웨거가 설정된 `Docket`이라는 객체를 반환
  * 반환하는`Docket`에 apiInfo, produces, consumes 메소드에 변수로 정의한 내용을 할당하여 커스터마이징할 수 있고 모델 자체에도 `@ApiModel` 어노테이션을 통해서 모델 자체나 필드에 각각 description or notes로 커스터마이징할 수 있음
* Actuator
  * API의 상태를 모니터링하는 기능
  * pom.xml의 라이브러리를 추가하기만해도 API의 헬스 정보를 파악할 수 있음
* HAL Browser
  * HATEOAS와 유사한 기능으로 API 리소스에 대해서 웹 브라우저에서 확인하는 기능
  * pom.xml의 라이브러리를 추가하기만해도 HATEOAS의 기능을 코드 상의 객체 선언 없이 HAL Borwser에서 쉽게 사용할 수 있음
* Security
  * `pring-boot-starter-security`를 추가하면 자동으로 패스워드가 하나 생성되고 이를 이용해서 접근 권한 처리가됨
  * yml 파일에 security 설정의 `WebSecurityConfigurerAdapter`을 통해서 사용자가 정의한 인증 정보를 추가할 수 있음
  * 추후에는 DB를 연동하여 이용하는 것이 바람직
  
Java Persistence API
=======
* JPA: 기존 JDBC의 대안으로 자바 ORM의 표준 API 명세
  * EntityManager를 통해서 CRUD를 처리 
  * JPA의 구현체는 `Hibernate` 라이브러리가 주로 사용되고 JPA는 `Spring Data JPA`의 구현되어 있는 함수와 인터페이스로 주로 이용함
* JPA 이용 방법
  * `@Entity` 어노테이션을 통해서 모델을 정의하면 테이블이 자동 생성되고 `resources` 아래에 정의된 sql 파일을 자동으로 읽어 실행
  * `JpaRepository<모델 이름, 기본 키>`을 상속받아 사용하여 레포지토리를 만들고 이 내부에 구현된 함수들을 통해서 디비관련 조작
  * find 관련 함수에서는 Optional<모델 이름>이 리턴이 되는데, 이는 결과가 비어 있을 수 있기 때문
  * 새로운 객체를 만들고 객체들간의 관계를 지으려면 `OneToOne`, `ManyToOne`, `OneToMany` 등등을 활용하여 매핑할 수 있음
  * `FetchType`을 통해서 관계를 갖고 있는 값들을 읽어 들이는 시기를 조절할 수 있음

  
RESTful API Design
=======
* api를 사용하는 이용자의 관점에서 설계
* Request, Response Type과 헤더 값 등등과 같은 HTTP 프로토콜 자체를 충분히 활용
* 각 리소스 별 적절한 HTTP 메소드를 제공
  * GET: 조회
  * POST: 생성
  * PUT: 수정
  * DELETE: 삭제
* HTTP 응답 상태 코드를 구분
* 토큰 발급 / 스프링 시큐리티 / 헤더 값 등등을 통해서 URI 안에 민감 정보 포함 X
* 복수 상태의 URI 사용
* 리소스에 대해서 `명사`를 사용
* 일관된 endpoint를 이용하고 URI 자체는 상태의 종류를 나타내지 않음
  * PUT /gist/{id}/star
  * DELETE /gist/{id}/star
* 특수하게 검색을 위해서는 `/serach` 메소드를 설계하고 여러 종류 URI로 다양한 요청 처리 가능
