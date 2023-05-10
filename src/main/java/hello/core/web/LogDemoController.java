package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLoggerObjectProvider; -> @Score(value="request",proxyMode=TargetCLass)적용전
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String demo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerObjectProvider.getObject(); 프록시 적용전

        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testID");
        return "OK";
    }
}
