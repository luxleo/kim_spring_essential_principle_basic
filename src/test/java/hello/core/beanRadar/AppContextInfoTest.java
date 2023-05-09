package hello.core.beanRadar;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 탐색")
    void findAllBean(){
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = ac.getBean(beanName);
            System.out.println("beanName+  = " + beanName+ " obj = "+ bean );
        }
    }
    @Test
    @DisplayName("모든 빈 탐색")
    void findAllAppBean(){
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
            // Role ROLE_APPLICATION: 직접 등록한 빈
            // Role ROLE_INFRASTRUCTURE: 스프링에서 등록한 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanName);
                System.out.println("beanName+  = " + beanName+ " obj = "+ bean );
            }

        }
    }
}
