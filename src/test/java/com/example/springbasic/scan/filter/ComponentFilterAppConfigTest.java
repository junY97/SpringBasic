package com.example.springbasic.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author junyeong.jo .
 * @since 2023-06-20
 */
public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();
        assertThrows(
                NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("beanB", BeanB.class)
        );

    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    /*
     * includeFilters 에 MyIncludeComponent 어노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.
     * excludeFilters 에 MyExcludeComponent 어노테이션을 추가해서 BeanB는 스프링 빈에 등록되지 않는다.
     *
     * !! FilterType 옵션
     * FilterType 은 5가지 옵션이 있다.
     *
     * 1. ANNOTATION: 디폴트값, 애노테이션을 인식해서 동작한다.
     * ex) org.example.SomeAnnotation
     *
     * 2. ASSIGNABLE_TYPE: 지정한 타입과 지식 타입을 인식해서 동작한다.
     * ex) org.example.SomeClass
     *
     * 3) ASPECTJ: AspectJ 패턴 사용
     * ex) org.example..*Service+
     *
     * 4) REGEX 정규 표현식
     * ex) org\.example\.Default.*
     *
     * 5) CUSTOM: TypeFilter 이라는 인터페이스르 구현해서 처리
     * ex) org.example.MyTypeFilter
     */
    static class ComponentFilterAppConfig {

    }
}
