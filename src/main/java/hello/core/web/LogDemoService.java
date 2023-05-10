package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    //private final ObjectProvider<MyLogger> myLoggerObjectProvider; 프록시 적용전
    private final MyLogger myLogger;
    public void logic(String id) {
        //MyLogger myLogger = myLoggerObjectProvider.getObject(); 적용전
        myLogger.log("service id = "+id);
    }
}
