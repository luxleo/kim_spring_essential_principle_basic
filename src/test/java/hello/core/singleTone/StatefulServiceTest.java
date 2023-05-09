package hello.core.singleTone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleTone(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread A: userA order items which price is 10000
        statefulService1.order("userA",10000);
        //Thread B: userB order items which price is 20000
        statefulService2.order("userB",20000);

        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        // 사용자 A가 주문한 금액이 다르게 나온다.
        Assertions.assertThat(price).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}