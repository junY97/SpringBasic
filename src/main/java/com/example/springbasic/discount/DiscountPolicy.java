package com.example.springbasic.discount;

import com.example.springbasic.member.Member;

/**
 * @author junyeong.jo .
 * @since 2023-06-08
 */
public interface DiscountPolicy {
    /**
     *
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
