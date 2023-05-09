package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 관례적으롤 인터페이스를 구현체가 하나인 경우 Impl로 이름 붙인다.
@Component
@RequiredArgsConstructor
public class MemberServiceImpl  implements MemberService{
    // DI방식 적용하기 전
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // DI 방식 적용 by constructor
    public final MemberRepository memberRepository;

    /* @RequiredArgsConstructor효과와 같다.
    @Autowired // Component와 함께 사용할때 의존성 주입을 위하여 어노테이션 사용
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

     */
    //스프링 싱글톤 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
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
