package com.example.springbasic.order;

import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.member.Member;
import com.example.springbasic.member.MemberRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author junyeong.jo .
 * @since 2023-06-08
 */
@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    /*
     * 조회 빈이 똑같은 타입이 2개 이상일 시 해결법
     *
     * 1. @Autowired
     * 2. @Qualifier
     * 3. @Primary
     *
     * @Primary, @Qualifier 활용
     * 코드에서 자주 사용하는 메인 데이터베이스의 커넥션을 획득하는 스프링 빈이 있고, 코드에서 특별한 기능으로
     * 가끔 사용하는 서브 데이터베이스 커넥션을 획득하는 스프링 빈이 있다고 생각해보자.
     * 메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 @Primary를 적용해서 조회하는 곳에서 @Qualifier 지정 없이
     * 편리하게 조회하고, 서브 데이터베이스 커넥션 빈을 획득 할 때는 @Qualifier를 지정해서 명시적으로 획득하는 방식
     * 으로 사용하면 코드를 깔끔하게 유지할 수 있다. 물론 이때 메인 데이터베이스의 스프링 빈을 등록할 때 @Qualifier를
     * 지정해주는 것은 상관없다.
     *
     * `우선순위`
     * @Primary는 기본값 처럼 동작하는 것이고, @Qualifier는 매우 상세하게 동작한다. 이런 경우 어떤 것이 우선권을 가져갈까?
     * 스프링은 자동보다는 수동이, 넓은 범위의 선택권 보다는 좁은 범위의 선택권이 우선 순위가 높다. 따라서 여기서도
     * @Qualifier가 우선권이 높다.
     */


    /*
     * 다양한 의존관계 주입 방법
     * 의존관계 주입은 크게 4가지 방법이 있다.
     * 생성자 주입
     * 수정자 주입 (setter 주입)
     * 필드 주입
     * 일반 메서드 주입
     *
     * 1. 생성자 주입
     * 이름 그대로 생성자를 통해서 의존관계를 주입받는 방법이다.
     * 특징
     * 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다.
     * 불변, 필수 의존관계에 사용
     *
     * ex
     *  @Autowired
     *   public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
     *       this.memberRepository = memberRepository;
     *       this.discountPolicy = discountPolicy;
     *   }
     * 중요!! 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당된다.
     *
     *
     * 2. 수정자 주입 (setter 주입)
     * setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다.
     * 특징
     * 선택, 변경 가능성이 있는 의존관계에 사용
     * 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
     *
     * ex
     *  @Autowired
     *   public void setMemberRepository (MemberRepository memberRepository) {
     *       this.MemberRepository = memberRepository;
     *   }
     *  @Autowired
     *   public void setDiscountPolicy(DiscountPolicy discountPolicy) {
     *       this.DiscountPolicy = discountPolicy;
     *   }
     *
     *
     * 3. 필드 주입
     * 이름 그대로 필드에 바로 주입하는 방법이다.
     * 특징
     * 코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
     * DI 프레임워크가 없으면 아무것도 할 수 없다.
     * 사용하지말자!
     * 애플리케이션의 실제 코드와 관계없는 테스트코드
     * 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용
     *
     * ex
     *  @aAutowired private final MemberRepository memberRepository;
     *  @Autowired private final DiscountPolicy discountPolicy;
     *
     *
     * 4. 일반 메서드 주입
     * 일반 메서드를 통해서 주입 받을 수 있다.
     * 특징
     * 한번에 여러 필드를 주입받을 수 있다.
     * 일반적으로 잘 사용하지 않는다.
     *
     * ex
     *  private MemberRepository memberRepository;
     *  private DiscountPolicy discountPolicy;
     *
     * @Autowired
     * public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
     *      this.memberRepository = memberRepository;
     *      this.discountPolicy = discountPolicy;
     * }
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
