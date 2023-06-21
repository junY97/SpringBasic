package com.example.springbasic.autowired;

import com.example.springbasic.AutoAppConfig;
import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.member.Grade;
import com.example.springbasic.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author junyeong.jo .
 * @since 2023-06-21
 */
public class AllBeanTest {
    /*
     * 로직분석
     * DiscountService 는 Map 으로 모든 DiscountPolicy 를 주입 받는다. 이때 fixDiscountPolicy, rateDiscountPolicy 가 주입된다.
     * discount() 메서드는 discountCode 로 fixDiscountPolicy 가 넘어오면 map 에서 fixDisCountPolicy 스프링 빈을 찾아서 실행한다.
     * 물론 rateDiscountPolicy 가 넘어오면 rateDiscountPolicy 를 찾아서 실행한다.
     *
     *
     * 주입분석
     * Map<String, DiscountPolicy>: Map 의 키에 스프링 빈의 일므을 넣어주고, 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링
     * 빈을 담아준다.
     * List<DiscountPolicy>: DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
     * 만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map 을 주입한다.
     */

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 31000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member, 31000, "rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(3100);

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }

    }
}
