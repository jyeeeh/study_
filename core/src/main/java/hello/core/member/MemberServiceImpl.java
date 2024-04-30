package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //interface만 갖고있으면 nullpointer except 발생
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        // join해서 save호출 시
        // 다형성에 의해서 Repository에 있는 save 호출
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
