package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscount;
import hello.core.discount.RateDiscount;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    /* DI 적용하기 전 OCP(open close principle), DIP(dependency inversion principle)을 지킬수 없다,
    private final MemberRepository memberRepository= new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscount();
    //private final DiscountPolicy discountPolicy = new RateDiscount();
     */

    // DI 적용(생성자를 통해 의존관계(리포지토리, 할인 정책)를 주입한다
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
