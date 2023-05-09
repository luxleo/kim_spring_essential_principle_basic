package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class CompFilterAppConfigTest {
    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        BeanA beanA = ac.getBean(BeanA.class);

        Assertions.assertThat(beanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean(BeanB.class));

    }
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComp.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyExcludeComp.class)
    )
    static class TestConfig{
    }
}
