package com.example.springbasic.order;

import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.discount.FixDiscountPolicy;
import com.example.springbasic.member.Member;
import com.example.springbasic.member.MemberRepository;
import com.example.springbasic.member.MemoryMemberRepository;

/**
 * @author junyeong.jo .
 * @since 2023-06-08
 */
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
        -문제점 발견
     1. 우리는 역할과 구현을 충실하게 분리했다 -> OK
     2. 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다. -> OK
     3. OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다.
     -> 그렇게 보이지만 사실은 아니다.


     DIP: 주문서비스 클라이언트 (OrderServiceImpl)는 DiscountPolicy 인터페이스에
     의존하면서 DIP를 지킨 것 같은데?

     -> 클래스의 의존관게를 분석해보면 추상 인터페이스 뿐만 아니라 "구현" 클래스에도
     의존하고 있다.

      ● 추상 인터페이스 의존 : DiscountPolicy
      ● 구현 클래스 : FixDiscountPolicy, RateDiscountPolicy

     OCP: 변경하지 않고 확장할수 있다고 했는데!
     -> 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP를 위반한다.
     */
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
