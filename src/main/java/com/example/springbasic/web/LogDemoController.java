package com.example.springbasic.web;

import com.example.springbasic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author junyeong.jo .
 * @since 2023-06-23
 */
@Controller
@RequiredArgsConstructor
public class LogDemoController  {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject(); // request 요청이 오면 스프링 컨테이너로 부터 객체 요청
        System.out.println("myLogger = " + myLogger.getClass()); // 결과를 확인해보면 우리가 등록한 순수한 myLogger
        // 가 아닌 `class com.example.springbasic.common.MyLogger$$SpringCGLIB`이라는 클래스로 만들어진 객체가 대신 등록되있다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("test id");
        return "OK";
    }

}
