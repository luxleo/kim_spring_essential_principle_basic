package hello.core.singleTone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneTest {
    @Test
    @DisplayName("요청이 올때마다 새로운 객체 생성")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // request 1
        MemberService memberService1 = appConfig.memberService();
        //request 2
        MemberService memberService2 = appConfig.memberService();
        //참조값 다른 것 확인 (새로운 객체생성)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleToneServiceTest() {
        // new SingleToneService() -> 컴파일 에러 난다.
        SingleToneService singleToneService1 = SingleToneService.getInstance();
        System.out.println("singleToneService1 = " + singleToneService1);

        SingleToneService singleToneService2 = SingleToneService.getInstance();
        System.out.println("singleToneService2 = " + singleToneService2);

        Assertions.assertThat(singleToneService1).isSameAs(singleToneService2);
    }

    @Test
    @DisplayName("스프링 컨테이너는 싱글톤의 모든 단점을 해결한 싱글톤 컨테이너이다.")
    void springContainerIsSingleTone(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값 같은 것 확인 (새로운 객체생성)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
