package hello.core.beanScope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBeanTest.class);

        System.out.println("프로토타입 빈 생성 1");
        PrototypeBeanTest bean1 = ac.getBean(PrototypeBeanTest.class);
        System.out.println("프로토타입 빈 생성 2");
        PrototypeBeanTest bean2 = ac.getBean(PrototypeBeanTest.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isNotSameAs(bean2);

        ac.close(); // 프로토타입은 빈의 생성,의존 주입, 초기화 만 진행 하고 관리는 클라이언트가 하므로 PreDestroy가 호출 되지 않는다.
    }

    @Scope("prototype")
    static class PrototypeBeanTest{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeTest.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeTest.destroy");
        }
    }
}
