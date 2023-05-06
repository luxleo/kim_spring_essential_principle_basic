package hello.core.member;

// 관례적으롤 인터페이스를 구현체가 하나인 경우 Impl로 이름 붙인다.
public class MemberServiceImpl  implements MemberService{
    // DI방식 적용하기 전
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // DI 방식 적용 by constructor
    public final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
