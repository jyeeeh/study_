package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    //10% 할인이 되는지
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    //JUnit5 부터 지원 : @DisplayName
    @DisplayName("VIP는 10% 할인이 적용되어야 함")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member,10000);

        //then : 할인된 금액이 1000 이어야함
        assertThat(discount).isEqualTo(1000);
    }

    //실패 test
    @Test
    @DisplayName("VIP가 아니면 할인이 안되어야함")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member,10000);

        //then : 할인된 금액이 1000 이어야함
        assertThat(discount).isEqualTo(1000);
    }

    /*
    * 결과 :
    * Expected :1000
      Actual   :0
    * */

}