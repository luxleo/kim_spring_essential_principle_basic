package hello.core.singleTone;

public class SingleToneService {
    private static final SingleToneService instance = new SingleToneService();
    public static SingleToneService getInstance(){
        return instance;
    }
    // private 으로 생성자 제한 함으로 외부에서 클래스 생성이 불가능 하게 한다.
    private SingleToneService(){

    }
    public void login(){
        System.out.println("싱글톤 객체 호출");
    }
}
