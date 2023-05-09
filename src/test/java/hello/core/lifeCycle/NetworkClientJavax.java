package hello.core.lifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClientJavax {
    private String url;

    public NetworkClientJavax(){
        System.out.println("Javax 빈 라이프사이클 객체 생성!");
    }
    public void setUrl(String url) {
        this.url = url;
    }
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
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init javax");
        connect();
        call("초기화 연결 메시지");
    }
    // DisposableBean: 빈 종료시 호출
    @PreDestroy
    public void destroy() {
        System.out.println("NetworkClient.destroy javax");
        disconnect();
    }
}
