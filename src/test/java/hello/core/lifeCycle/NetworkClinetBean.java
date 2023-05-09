package hello.core.lifeCycle;

public class NetworkClinetBean {
    private String url;

    public NetworkClinetBean(){
        System.out.println("빈 베이스 생명주기 호출");
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
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
    // DisposableBean: 빈 종료시 호출
    public void destroy() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
