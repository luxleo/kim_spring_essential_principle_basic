package hello.core.lifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;
    public NetworkClient(){
        System.out.println("call constructor, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }
    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect := " + url);
    }
    public void call(String msg){
        System.out.println("call "+ url+" message: "+msg);
    }
    public void disconnect(){
        System.out.println("close := " + url);
    }

    // InitializingBean: 의존 관계 주입 종료후 호출 되는 메서드
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
    // DisposableBean: 빈 종료시 호출
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
