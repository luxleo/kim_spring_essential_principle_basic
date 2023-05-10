package hello.core.beanScope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletoneTest {
    @Test
    void findSingletone(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletoneBean.class);

        SingletoneBean sing1 = ac.getBean(SingletoneBean.class);
        SingletoneBean sing2 = ac.getBean(SingletoneBean.class);

        Assertions.assertThat(sing1).isSameAs(sing2);

        ac.close();
    }
    @Scope("singleton")
    static class SingletoneBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletoneBean.init");
        }
        @PreDestroy
        public void close(){
            System.out.println("SingletoneBean.close");
        }
    }
}
