package com.example.springbasic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * @author junyeong.jo .
 * @since 2023-06-22
 */
public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // 조회할때마다 새로운 인스턴스 반환
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); // 조회할때마다 새로운 인스턴스 반환

        System.out.println("prototypeBean1 = " + prototypeBean1); 
        System.out.println("prototypeBean2 = " + prototypeBean2); 

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2); // 서로 다른 인스턴스이므로 다름

        ac.close();
        /*
         * 싱글톤빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행되지만,
         * 프로토타입 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고, 초기화 메서드도 실행된다.
         *
         * 프로토타입 빈을 2번 조회했으므로 완전히 다른 스프링 빈이 생성되고,초기화도 2번 실행된 것을 확인할 수 있다.
         * 스프링 컨테이너가 생성과 의존관계 주입 그리고 초기화까지만 관여하고, 더는 관리하지 않는다.
         * 따라서 프로토타입 빈은 스프링 컨테이너가 종료될 때 `@PreDestroy`같은 종료 메서드가 전혀 실행되지 않는다.
         *
         */

        prototypeBean1.destroy();
        prototypeBean2.destroy();

    }
    /*
     * ※ 프로토타입 스코프의 특징
     * 프로토타입 스코프의 빈을 스프링 컨테이너에서 조회하면 컨테이너에서
     * 항상 새로운 인스턴스를 생성해서 반환한다.
     *
     * 종료메서드가 호출되지 않는다.
     *
     * 그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다.
     * 종료메서드에 대한 호출도 클라이언트가 직접해야한다.
     */
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
