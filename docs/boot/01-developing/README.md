# Developing

## Structuring

### “default” Package

클래스에 패키지 선언이 포함되어 있지 않으면 "기본 패키지"에 있는 것으로 간주하며 "기본 패키지"의 사용은 권장되지 않음

Java의 이름 지정 규칙을 따르고 반전된 도메인 이름(예: com.example.project)을 사용하는 것을 권

### Main Class 위치

Main Class(`[@SpringBootApplication](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/#using.using-the-springbootapplication-annotation)`이 적용된 Class)는 루트 패키지에 위치하는 것이 좋음

```jsx
com
 +- example
     +- myapplication
         +- MyApplication.java // main class
         |
         +- customer
         |   +- Customer.java
         |   +- CustomerController.java
         |   +- CustomerService.java
         |   +- CustomerRepository.java
         |
         +- order
             +- Order.java
             +- OrderController.java
             +- OrderService.java
             +- OrderRepository.java
```

## Configuration

- Spring Boot는 Java 기반 설정인 단일 @Configuration 클래스를 사용하는 것이 좋음
- main 메서드가 정의된 클래스를 기본 @Configuration으로 사용해도 됨
- 가능하면 Java 기반 구성을 사용
- Enable* 애너테이션?

### **Additional Configuration Classes**

- @Configuration을 하나의 클래스에 모두 구성하지 않아도 됨
- @import 애너테이션을 사용하여 설정 클래스를 가져올 수 있음
- 또는 @ComponentScan을 사용하여 @Configuration 클래스를 포함한 모든 Spring 컴포넌트를 자동으로 가져올 수 있음

### **XML Configuration**

@ImportResource 애너테이션을 사용하여 XML 구성 파일을 로드할 수 있음

## Auto Configuration

사용자가 추가한 jar 종속성을 기반으로 Spring 애플리케이션을 자동으로 구성함

HSQLDB가 클래스 경로에 있고 데이터베이스 연결 빈을 수동으로 구성하지 않은 경우 Spring Boot는 인메모리 데이터베이스를 자동으로 구성

기본 @Configuration 클래스에 @EnableAutoConfiguration 또는 @SpringBootApplication 애너테이션 중 하나를 선택 해서 추가해야 함 (둘 중 하나만 추가해야 함)

### Auto-configuration 점진적 대체

자동 구성의 특정 부분을 대체하기 위해 직접 구성을 정의할 수 있고 자체 설정은 추가하면 기본 설정은 사라짐

현재 어떤 자동 구성이 적용되고 있는지 확인하려면 --debug 를 사용하여 앱을 실행하면 됨

### Auto-configuration 비활성화

@SpringBootApplication의 exclude 속성을 사용하여 @Configuration 클래스를 비활성화 할 수 있음

```java
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MyApplication {}
```

- excludeName 속성을 사용하여 이름으로 대신 지정할 수 있음
- @EnableAutoConfiguration을 사용하는 경우에도 가능
- properties 설정에서 spring.autoconfigure.exclude 속성을 사용하여 제외할 수도 있음

## Dependency Injection

일반적으로 생성자(constructor)를 사용하여 DI 하는 것이 좋음

@ComponentScan 또는 이를 포함하는 @SpringBootApplication 애너테이션을 사용

모든 애플리케이션 컴포넌트(@Component, @Service, @Repository, @Controller 등)는 자동으로 Spring Bean으로 등록함

```java
// 생성자 기반 DI
@Service
public class MyAccountService implements AccountService {
    private final RiskAssessor riskAssessor;
    
    public MyAccountService(RiskAssessor riskAssessor) {
        this.riskAssessor = riskAssessor;
    }
}

// 생성자가 하나 이상인 경우 @Autowired를 사용
@Service
public class MyAccountService implements AccountService {
    private final RiskAssessor riskAssessor;
    private final PrintStream out;

    @Autowired
    public MyAccountService(RiskAssessor riskAssessor) {
        this.riskAssessor = riskAssessor;
        this.out = System.out;
    }

    public MyAccountService(RiskAssessor riskAssessor, PrintStream out) {
        this.riskAssessor = riskAssessor;
        this.out = out;
    }

    // ...

}
```

## **@SpringBootApplication**

@SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
3개의 애너테이션을 통합한 것

- EnableAutoConfiguration: Spring Boot의 auto-configuration을 활성화
- ComponentScan: 애플리케이션이 있는 패키지에서 @Component 클래스들을 스캔
- SpringBootConfiguration: 컨텍스트에서 빈을 등록하거나 설정 클래스를 가져올 수 있도록 함
  통합 테스트에서 구성 감지를 지원하는 Spring의 표준 @Configuration의 대안