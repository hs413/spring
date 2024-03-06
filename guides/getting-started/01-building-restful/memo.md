### AtomicLong

멀티 스레드 환경에서 원자적으로 연산을 수행할 수 있도록 도와주는 클래스
(스레드간의 경쟁이 없도록 보장)

여러 스레드에서 동시에 접근하여 값이 변경되는 경우에 사용

일반적으로 Thread-Safe한 연산을 구현할 때 사용되며, 병렬 처리, 동시성 제어, 카운팅 등에 유용하게 쓰일 수 있음


### @SpringBootApplication
아래 애너테이션을 추가

- @Configuration: 애플리케이션 컨텍스트에 대한 Bean 정의의 소스로 클래스에 태그를 지정

- @EnableAutoConfiguration: classpath 설정, other beans, various property 설정 기반으로 Bean 등록
 
- @ComponentScan: 패키지 내 components, configurations, services 등의 컴포넌트를 스캔하여 등록