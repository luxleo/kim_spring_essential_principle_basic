package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscount implements DiscountPolicy{
    private int discountAmount = 1000;// 할인 금액 처넌
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) return price-discountAmount;
        else return 0;
    }
}
