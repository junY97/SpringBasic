package com.example.springbasic.web;

import com.example.springbasic.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

/**
 * @author junyeong.jo .
 * @since 2023-06-23
 */
@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger;

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject(); // request 요청이 오면 스프링 컨테이너로 부터 객체 요청
        myLogger.log("service id = " + id);
    }
}
