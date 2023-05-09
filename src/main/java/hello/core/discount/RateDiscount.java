package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // 커스텀 @Qualifier annotation으로 설정
public class RateDiscount implements DiscountPolicy{
    private int discountPercent = 10; // 10프로 할인
    @Override
    public int discount(Member member, int price) {
        // 할인해주는 가격을 반환한다.
        if(member.getGrade() == Grade.VIP) return price-price*discountPercent/100;
        else return 0;
    }
}
