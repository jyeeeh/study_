package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class MemberServiceTest {

    MemberService memberService;

    //test실행 전 실행
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

//    @Test
//    public void join(){
//        //given
//        Member member = new Member(1L, "memberA", Grade.VIP);
//
//        //when
//         memberService.join(member);
//        Member findMember = memberService.findMember(1L);
//
//        //then
//        Assertions.assertThat(member).isEqualTo(findMember);
//    }
}
