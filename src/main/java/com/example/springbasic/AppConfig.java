package com.example.springbasic;

import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.discount.FixDiscountPolicy;
import com.example.springbasic.member.MemberRepository;
import com.example.springbasic.member.MemberService;
import com.example.springbasic.member.MemberServiceImpl;
import com.example.springbasic.member.MemoryMemberRepository;
import com.example.springbasic.order.OrderService;
import com.example.springbasic.order.OrderServiceImpl;

/**
 * @author junyeong.jo .
 * @since 2023-06-12
 */
public class AppConfig {
    /**
     * AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
     * MemberServiceImpl
     * MemoryMemberRepository
     * OrderServiceImpl
     * FixDiscountPolicy
     *
     * AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입 해준다.
     */
    public MemberService memberService () {
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }


}
