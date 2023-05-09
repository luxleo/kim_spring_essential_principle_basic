package hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleTestConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Test
    public void lifeCycleBeanTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleTestConfig.class);
        NetworkClinetBean client = ac.getBean(NetworkClinetBean.class);
        ac.close();
    }
    @Test
    public void lifeCycleTestJavaX(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleTestConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleTestConfig{
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://dragon_is_me.dev");
            return networkClient;
        }
        @Bean(initMethod = "init", destroyMethod = "destroy")
        public NetworkClinetBean networkClinetBean(){
            NetworkClinetBean client = new NetworkClinetBean();
            client.setUrl("http://dragon_javax.dev");
            return client;
        }
        @Bean
        public NetworkClientJavax networkClientJavax(){
            NetworkClientJavax networkClient = new NetworkClientJavax();
            networkClient.setUrl("http://dragon_is_me.dev");
            return networkClient;
        }
    }
}