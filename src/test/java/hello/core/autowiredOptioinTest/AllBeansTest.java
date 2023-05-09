package hello.core.autowiredOptioinTest;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeansTest {
    @Test
    void showAllBeans(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService bean = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "memA", Grade.VIP);
        int discountPrice = bean.discount(member, 10000, "rateDiscount");

        assertThat(bean).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(9000);

    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountPolicyKey) {
            DiscountPolicy discountPolicy = policyMap.get(discountPolicyKey);
            return discountPolicy.discount(member, price);
        }
    }
}
