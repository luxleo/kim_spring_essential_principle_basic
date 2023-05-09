package hello.core.xml;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppCtxTest {
    @Test
    @DisplayName("xml로 스프링 설정")
    void XMLApp(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService mem = ac.getBean(MemberService.class);
        assertThat(mem).isInstanceOf(MemberServiceImpl.class);
    }

}
