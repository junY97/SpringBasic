package com.example.springbasic.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

/**
 * @author junyeong.jo .
 * @since 2023-06-23
 */
public class PrototypeProviderTest {

    /*
     * 프로토타입 스코프, 싱글톤 빈과 함께 Provider로 문제 해결
     * 싱글톤 빈과 프로토 타입빈을 함께 사용할 때, 어떻게 하면 사용할 때 마다 항상 새로운 프로토타입 빈을 생성할수 있을까?
     *
     * 1. 직접적으로 스프링 컨테이너(어플리케이션 컨텍스트)를 주입받는다.
     *
     * 2. ObjectProvider, ObjectFactory
     * 기능 단순, 별도 라이브러리가 필요없음, 스프링에 의존적이다.
     *
     * 3. JSR-330 Provider
     * `javax.inject.Provider`라는 JSR-330 자바 표준을 사용하는 방법이다.
     *  이 방법을 사용하려면 `javax.inject:javax.inject:1` 라이브러리를 gradle 에 추가해야 한다.
     */

    @Test
    void providerTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }


    @Scope("singleton")
    static class ClientBean {

        private final ObjectProvider<PrototypeBean> prototypeBeanProvider; // 지정한 빈을 컨테이너에서 찾아줌
        ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }
//        private final Provider<PrototypeBean> prototypeBeanProvider;
//
//        ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
//            this.prototypeBeanProvider = prototypeBeanProvider;
//        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 내부에서 스프링 컨테이너를 통해 해당 빈을 찾아서 반환
//            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // 내부에서 스프링 컨테이너를 통해 해당 빈을 찾아서 반환

            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
