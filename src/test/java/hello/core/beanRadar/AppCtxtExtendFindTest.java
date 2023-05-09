package hello.core.beanRadar;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscount;
import hello.core.discount.RateDiscount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppCtxtExtendFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상이면 중복 오류 발생")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("자식 타입이 중복 되면 이름이나 구체 타입으로 특정하여 지정가능")
    void findBeanByParentTypeSolve(){
        DiscountPolicy fixDiscount = ac.getBean("fixDiscount", DiscountPolicy.class);
        DiscountPolicy bean = ac.getBean(RateDiscount.class);

        assertThat(fixDiscount).isInstanceOf(DiscountPolicy.class);
        assertThat(bean).isInstanceOf(DiscountPolicy.class);
    }
    @Test
    @DisplayName("부모 타입으로 해당 config의 모든 bean 조회 -Object")
    void findAllBeanByObject(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = "+ beansOfType.get(key));
        }
    }
    @Configuration
    static class TestConfig {
        @Bean
        DiscountPolicy fixDiscount() {
            return new FixDiscount();
        }

        @Bean
        DiscountPolicy rateDiscount() {
            return new RateDiscount();
        }
    }
}
