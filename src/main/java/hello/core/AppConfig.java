package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscount;
import hello.core.discount.RateDiscount;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // AppConfig등장으로 어플리케이션의 구성영역, 사용영역으로 분리가 가능하고 ,어플의 구성의 변경이 생기면(ex: 레포의 변경, 정책의 변경)구성영역만 변경
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        // DI를 통해 리포지토리는 MemoryMemberRepository, 할인 정책은 FixDiscount로 주입
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }
    @Bean
    // refactoring 적용하여 중복(new MemoryMemberRepository)제거, 기능 명시화
    public DiscountPolicy getDiscountPolicy() {
        //return new FixDiscount();
        return new RateDiscount();
    }
    @Bean
    public MemberRepository getMemberRepository()
    {
        System.out.println("call AppConfig.getMemberRepository");
        return new MemoryMemberRepository();
    }

}
