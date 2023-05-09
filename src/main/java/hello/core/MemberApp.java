package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //psvm 단축키로 생성
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();

        // AppConfig에 등록된 Bean객체를 스프링이 관리한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member memberA = new Member(1L, "memberA", Grade.VIP); // cmd + option + v 로 자동 선언 및 할당

        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName()); // soutv로 선언된 변수 고르기
        System.out.println("findMember = " + findMember.getName());
    }
}
