package com.example.springbasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author junyeong.jo .
 * @since 2023-06-19
 */

@Configuration
@ComponentScan(
        /*
         * basePackages : 탐색할 패키지 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
         * basePackages = {"hello.core", "hello.service"} 이렇게 여러 시작 위치를 지정할 수도 있다.
         * basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
         * 만약 지정하지 않으면, @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다. 현재 AutoAppConfig의 경우
         * com.example.springbasic 패키지가 시작위치가 되는 것이다.
         *
         * !!권장하는 방법 : 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
         * 최근 스프링 부트도 이 방법을 기본으로 제공한다.
         *
         */

        //        basePackages = "com.example.springbasic.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/*
 * 참고 : 컴퍼넌트 스캔을 사용하면 `@Configuration`이 붙은 설정 정보도 자동으로 등록되기 때문에, ,AppConfig,
 * TestConfig 등 앞서 만들어두었던 설정 정보도 함계 등록되고, 실행되어 버린다. 그래서 `excludeFilters`를 이용해서
 * 설정정보는 컴퍼넌트 스캔 대상에서 제외했다. 보통 설정 정보를 컴퍼넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대
 * 한 남기고 유지하기 위해서 이 방법을 선택했다.
 */
public class AutoAppConfig {

}
