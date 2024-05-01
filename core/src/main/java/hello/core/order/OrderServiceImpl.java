package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 찾기
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 고정할인정책 -> 유연하게 변경
    // 기능을 확장해서 변경하면 class 수정을 해야하므로 [OCP 위반]
//  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    -> 아래와 같이 변경
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종 생성된 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
