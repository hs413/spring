## **SpringApplication**

### **Lazy Initialization**

지연 초기화를 활성화하면 애플리케이션을 시작할 때가 아니라 필요할 때마다 빈이 생성되며 실행 시간을 단축할 수 있음

웹 애플리케이션에서 활성화하면 HTTP 요청이 수신 될 때까지 빈이 초기화되지 않음

애플리케이션 시작 시 오류를 발견하지 못하게 되며 문제 발견이 지연될 수 있고, 메모리 관리가 어려움 → 따라서 지연 초기화는 기본적으로 사용하지 않음, 지연 초기화를 활성화하기 전에 JVM의 힙 크기 조정 필요

## **Externalized Configuration (설정 파일)**

설정 파일은 일관된 포맷으로 사용하는 것을 권장

.properties와 yaml 포맷이 있는 경우 .properties 우선

### **Command Line Properties**

SpringApplication은 모든 명령줄 옵션 인수 (--으로 시작하는 인수, --server.port=9000)를 속성으로 변환하여 Spring 환경에 추가함

명령줄 프로퍼티는 파일 기반 프로퍼티 보다 우선순위가 높음

SpringApplication.setAddCommandLineProperties(false)를 사용하여 비활성화할 수 있음

## Profiles

@Profile을 사용해서 특정 환경의 설정을 구성할 수 있음

```java
@Configuration(proxyBeanMethods = false)
@Profile("production")
public class ProductionConfiguration {
    // ...
}

// 우선순위
spring.profiles.include[0]=common
spring.profiles.include[1]=local

// 그룹
spring.profiles.group.production[0]=proddb
spring.profiles.group.production[1]=prodmq
```