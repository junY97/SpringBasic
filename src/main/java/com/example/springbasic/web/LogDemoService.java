package com.example.springbasic.web;

import com.example.springbasic.common.MyLogger;
import org.springframework.stereotype.Service;

/**
 * @author junyeong.jo .
 * @since 2023-06-23
 */
@Service
public class LogDemoService {
    private final MyLogger myLogger;

    public LogDemoService(MyLogger myLogger) {
        this.myLogger = myLogger;
    }

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
