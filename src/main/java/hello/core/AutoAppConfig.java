package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {
    /* 이렇게 수동으로 등록하면 자동으로 등록한 것을 오버라이딩한다.
    반대로 자동 vs 자동 충돌이 일어나면 컴파일 오류 발생
    @Bean
    MemberRepository manualTest(){
        return new MemoryMemberRepository();
    }
     */
}
