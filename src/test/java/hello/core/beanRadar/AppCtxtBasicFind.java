package hello.core.beanRadar;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class AppCtxtBasicFind {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 이름없이 인터페이스 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean( MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 이름없이 구체 타입으로 조회")
    void findBeanByClassType(){
        // 구체타입으로 조회하는 것은 바람직하지 않으나 여의치 않을 떄는 사용한다.
        MemberService memberService = ac.getBean( MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    // fail test case

    @Test
    @DisplayName("없는 이름으로 빈 조회시 에러발생")
    void findBeanWithNoName(){
        assertThrows(NoSuchBeanDefinitionException.class, ()->ac.getBean("nonExist", MemberService.class));
    }
}
