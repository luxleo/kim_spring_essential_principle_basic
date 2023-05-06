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
```

### Spring
```text
1. HashMap -> 실무에서는 ConcurrentHashMap
2. soutv에서 객체 인자로 전달시 객체의 toString호출 된다.
3. 2번에 따라 cmd+n으로 toString 오버라이드
4. IoC,DI 컨테이너:
- AppConfig 처럼 런타임에 의존 관계를 정하는 컨테이너
- 어셈블러, 오브젝트 팩토리 라고도 한다.
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
