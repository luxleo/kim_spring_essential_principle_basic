package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountTest {
    RateDiscount rateDiscount = new RateDiscount();

    @Test
    @DisplayName("vip 멤버는 10프로 할인이 되어야한다")
    void apply_vip_discount(){
        // given
        Member member = new Member(1L, "member", Grade.VIP);
        //when
        int discount = rateDiscount.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("vip 아닌 멤버는 할인 아님")
    void not_apply_discount(){
        // given
        Member member = new Member(2L, "member", Grade.BASIC);
        //when
        int discount = rateDiscount.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}