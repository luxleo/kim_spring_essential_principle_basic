package hello.core.beanScope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletoneWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.countUp();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.countUp();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class,DLClientBean.class);

        ClientBean clientBean = ac.getBean(ClientBean.class);
        int logic1 = clientBean.logic();
        Assertions.assertThat(logic1).isEqualTo(1);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int logic2 = clientBean1.logic();
        Assertions.assertThat(logic2).isEqualTo(2);
    }

    @Scope("singleton")
    static class DLClientBean{
        @Autowired private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.countUp();
            return prototypeBean.getCount();
        }
    }
    @Scope("singleton")
    static class ClientBean{
        private final PrototypeBean myPrototype; // 생성시점에 주입이 끝나므로 계속해서 한번 생성된 프로토타입 빈 사용한다.

        @Autowired
        public ClientBean(PrototypeBean myPrototype) {
            this.myPrototype = myPrototype;
        }

        public int logic(){
            myPrototype.countUp();
            return myPrototype.getCount();
        }
    }
    @Scope("prototype")
    static class PrototypeBean{
        private int count= 0;

        public void countUp(){
            count++;
            return;
        }

        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init"+this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
