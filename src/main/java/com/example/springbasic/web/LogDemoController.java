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
    private final ObjectProvider<MyLogger> myLoggerProvider; // ObjectProvider 덕분에 빈의 생성 지연가능
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject(); // request 요청이 오면 스프링 컨테이너로 부터 객체 요청
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("test id");
        return "OK";
    }

}
