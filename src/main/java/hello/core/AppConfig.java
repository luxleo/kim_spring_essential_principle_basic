package hello.core;

import hello.core.discount.FixDiscount;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService(){
        // DI를 통해 리포지토리는 MemoryMemberRepository, 할인 정책은 FixDiscount로 주입
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscount());
    }
}
