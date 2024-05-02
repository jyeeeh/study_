package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 전체 설정, 구성
// 실제 동작에 필요한 구현 객체를 생성
// 역할과, 구현클래스 한눈에 파악가능
public class AppConfig {

    // Repository 역할 표기 위해 분리작업
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository(){
        // 다른 구현체로 변경 시 해당 부분만 변경
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        // 고정할인정책 사용
        return new FixDiscountPolicy();
    }

}
