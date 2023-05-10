package hello.core.member;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>(); // 실무에서는 ConCurrentHashMap사용한다.
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
