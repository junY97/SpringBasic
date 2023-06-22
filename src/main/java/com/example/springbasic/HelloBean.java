package com.example.springbasic;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author junyeong.jo .
 * @since 2023-06-22
 */

/*
 * 빈 스코프란?
 * 지금까지 우리는 스프링 빈이 스프링 컨테이너의 시작과 함께 생성되어서 스프링 컨테이너가 종료될 때 까지
 * 유지된다고 학습했다. 이것은 스프링 빈이 기본적으로 싱글톤 스코프로 생성되기 때문이다.
 * 스코프는 번역 그대로 빈이 존재할 수 있는 범위를 뜻한다.
 *
 * "스프링은 다음과 같은 다양한 스코프를 지원한다."
 * 싱글톤: "기본스코프"이고 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프이다.
 * 프로토타입: 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지않는 매우 짧은 범위의 스코프이다.
 *
 * 웹 관련 스코프
 * request: 웹 요청이 들어오고 나갈때 까지 유지되는 스코프이다.
 * session: 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프이다.
 * application: 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프이다.
 */
@Scope("prototype")
@Component
public class HelloBean {
}
