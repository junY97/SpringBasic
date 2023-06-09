package com.example.springbasic.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author junyeong.jo .
 * @since 2023-06-23
 */

/*
 * `@Scope`의 proxyMode = ScopedProxyMode.TARGET_CLASS`를 설정하면 스프링 컨테이너는
 * CGLIB 라는 바이트 코드를 조작하는 라이브러리를 사용해서, MyLogger 를 상속받는 가짜 프록시 객체를 생성한다.
 *
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request bean create : " + this);
    }

    @PreDestroy
    public void  destroy() {
        System.out.println("[" + uuid + "]" + "request bean destroy : " + this);
    }

    /*
     * 로그를 출력하기 위한 클래스이다.
     *
     * `Scope(value = "request")`를 사용해서 request 스코프로 지정했다. 이게 이 빈은 HTTP 요청 당 하나씩 생성되고,
     * HTTP 요청이 끝나는 시점에 소멸된다.
     *
     * 이 빈이 생성되는 시점에 자동으로 `@PostConstruct` 초기화 메서드를 사용해서 uuid 를 생성해서 저장해둔다.
     * 이 빈은 Http 요청 당 하나씩 생성되므로, uuid 를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
     * 이 빈이 소멸되는 시점에 `@PreDestroy`를 사용해서 종료메시지를 남긴다.
     * `requestURL`은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력받는다.
     */

}
