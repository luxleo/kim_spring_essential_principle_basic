### gradle
```text
1. java11과 spring boot 2.x.x버전으로 해야 호환 가능 -> 3.x 부터는 java 17
2. intellij 에서 작업시 preference -> gradle build run test 모두 intellij로 변경 (더 빠르다)
3. command + n -> 객체 안에서 생성자, 게터 세터 만들기 + command + a로 전체 선택
```

### intelliJ 단축키
```text
1.soutv
2. psvm
3. cmd+option+v 자동 완성
4. cmd+e -> 히스토리 보여줌
5. cmd+shift+t -> class 별로 테스트 만들어준다.
6. ctrl+r -> 이전 명렬 실행
7. 프로젝트의 show diagram으로 정적(실행하지 않고) 의존 관계 파악 가능하다.
8. 배열 선언후 iter+ tab하면 자동 for문 생성
9. shift+shift => 프로젝트내 파일 탐색
10. lombok cmd+,(preference) -> annotation process enable
```

### Spring
```text
1. HashMap -> 실무에서는 ConcurrentHashMap
2. soutv에서 객체 인자로 전달시 객체의 toString호출 된다.
3. 2번에 따라 cmd+n으로 toString 오버라이드
4. IoC,DI 컨테이너:
- AppConfig 처럼 런타임에 의존 관계를 정하는 컨테이너
- 어셈블러, 오브젝트 팩토리 라고도 한다.
5. beanFactory <- ApplicationContext( 이 두 인터페이스를 스프링 컨테이너라 하고)
- ApplicationContext의 경우 더 많은 기능을 상속하여 사용(이벤트,메신저 등)
6. 스프링 빈은 항상 무상태이어야한다 (싱글톤 이므로 공유가 일어나서).
------ComponentScan
7. @ComponentScan 어노테이션으로 수식한 설정 파일은 @Component 어노테이션
 -으로 수식된 모든 클래스를 등록한다 이때 @Configuration도 내부적으로 @Component를 포함한다
 - @Autowired로 생성자를 수식하여 DI를 지키도록 한다.
8. @ComponentScan의 스캔 범위
- 기본: 해당 프로젝트루트
- basePackages 어트리뷰트: 탐색 시작할 루트를 지정 -> ex:"hello.core.member"
9. @ComponentScan의 스캔 대상 어노테이션:
- @Service,@Controller등
10. ComponentScan filter type: Annotation,지정 클래스
11. ComponentScan으로 자동 등록시 자동vs자동, 자동vs수동의 충돌이 일어나지 않게끔
- 모호성을 제거하자
----- 다양한 의존관계 주입 방법
12. 생성자:
- 생성자 호출시점에 딱 한번 호출 (불변,필수)
- 생성자가 하나일때는 자동으로 @Autowired 등록한다.
13. 수정자:
- 빈이 변경가능 할떄 사용(변경가능,선택적) 이떄 선택적은 @Autowired(required=false)로 구현
- 많이 사용되는 방식이 아니고 주입하고자 하는 필드의 setter로 구현한다.
14. 필드 주입:
- @Autowired private MemberRepository memberRepository;
와 같이 직접 필드에 등록하는 형태
- 테스트나 @Configuration의 클래스에만 사용하지 일반적으로는 사용하지 않는다.
- 코드가 깔끔해진다는 장점이 있으나, 스프링 없이 순수한 자바코드로만 테스트 코드 작성이 어려워지고
설정 변경이 매우 복잡하다(setter를 지정하영 변경)
15. 일반 메서드 주입:
- 여러 필드를 동시에 지정할 수있는 장점이 있으나
생성자, 수정자 주입으로 구현이 가능하고 스프링 빈에만 적용이 가능하다.
일반적으로 잘 사용하지 않는다.
---- @Autowired 옵션 처리 (빈이 없을때도 처리해야할 로직이 있는 경우)
16. @Autowired(required=false)
- 이 경우 빈이 없으면 아예 실행 되지 않는다.
17.@Nullable, Optional
--- 생성자 주입을 사용해야하는 이유
1. 누락된 주입이 있을시 바로 알 수있음
2. final 키워드를 적용할 수있는 유일한 주입 방법(변경 불가능을 보장)
--- lombok
@RequiredArgsConstructor로 자동 final 붙은 필드 주입하는 생성자 생성

--- 조회시 빈이 두개 있는 경우
1. @Autowired
2. @Qualifier: 상세히 지정 가능하고 @Qualifier("name") name에 해당하는 이름 없으면 빈을 찾는데
- 이 때 빈까지 탐색이 넘어가지 않게 구체적으로 이름을 정해주는 것이 좋다.
3. @Primary: 메인으로 사용하는 녀석으로 어노테이션 지정만 해두면 알아서 이녀석 사용
- 우선순위는 @Qualifier가 더 높기 때문에 특이사항이 있을시 @Qualifier 사용하여 변경하자.
4. custom @Qualifer annotation: cmd+o -> @Qualifer검색 -> 위의 어노테이션 카피후 새로운 @Qualifer 재정의 하여
- 컴파일시 @Qualifer이름이 달라지면 못잡는 경우를 봉쇄 -> 더 확실한 @Qualifer 이용가능

--- 등록된 특정 인터페이스 타입의 모든 빈이 필요할때
-> Map<String,TargetClass>, List<TargetClass>로 받아 해시나, 리스트로
- 특정 인터페이스(클래스) 타입의 빈을 모두 조회 할 수 있다.
---- 빈 라이프 사이클 관리
1. @InitializingBean(초기화 후), @DisposableBean(빈 종료후)
- 스프링에 종속적 이고, 인터페이스를 통으로 가져와 부담이 크다, 
2. @Bean(initMethod="init", destroyMethod="destroy") -> config에 설정시 등록
- 
3. @PostConstruct,@PreDestroy -> javax
- 자바 자체에서 제공한다. 가장 권장 되는 방식
```

### 객체지향
```text
0. DIP(Dependency Inversion policy): 객체는 추상에 의존해야하며, 구체에 의존 해서는 안된다(인터페이스 o, class x)
1. 클라이언트의 DIP,OCP 원리가 위배 되는 상황을 구성부(AppConfig),클라이언트(OrderServiceImpl,MemberServiceImpl)
으로 나누어 DIP,OCP를 지키도록 구현( 책임을 명확하게, 배우가 상대역 배우 섭외 작업을 맡지 않도록)
2. 프레임워크, 라이브러리: 프레임 워크는 제어의 역전(IOC) 즉 형식에 맞추면 자동실행
라이브러리: 라이브러리는 개발자가 흐름을 제어 해야한다.
3. IOC vs DI: 
- 일반성:IOC > DI(IOC는 JUNIT과 같은 프레임 워크도 적용가능하다.)
```
