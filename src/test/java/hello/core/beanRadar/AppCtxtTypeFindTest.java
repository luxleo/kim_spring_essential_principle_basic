package hello.core.beanRadar;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AppCtxtTypeFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 오류 발생")
    void findBeanType(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(MemberRepository.class));
    }
    @Test
    @DisplayName("같은 타입의 bean두개 이상이면 이름을 지정하여 NoUnique에러를 해결")
    void findBeanByNameAndType(){
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입의 빈 모두 조회하기")
    void findAllBeansByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s+ " Value = "+beansOfType.get(s));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameBeanConfig {
        @Bean
        MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
