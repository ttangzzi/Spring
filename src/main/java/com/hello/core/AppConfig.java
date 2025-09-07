package com.hello.core;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.member.MemoryMemberRepository;
import com.hello.core.order.OrderService;
import com.hello.core.order.OrderServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링은 설정 정보에 @Configuration이라고 적는다.
@Configuration
// 이제 객체의 생성과 연결은 AppConfig가 담당한다.
public class AppConfig {
    // 각 메서드에 Bean을 적어주면 스프링 컨테이너에 등록된다.

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository(), ... 2번 호출 : 싱글톤이 깨지는게 아닐까?
    // 이렇게 의문이 들때는 테스트코드를 작성해 실행해본다.

    @Bean
    public MemberService memberService() {
        // 생성자를 통해서 MemoryMemberRepository 구현체를 선택한다.
        // 이것을 생성자를 통해 new instance가 들어간다고 해서 "생성자 주입"이라고 한다.
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // 리팩토링 : 역할들에 대한 구현을 한 눈에 들어오도록 하기.
    // 나중에 DB 저장소로 바꾸게되면 이 코드만 바꾸면 된다.

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        // 생성자 주입을 통해 MemoryMemberRepository와 FixDiscountPolicy 구현체를 선택한다.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 리팩토링 : 역할들에 대한 구현을 한 눈에 들어오도록 하기.
    // 나중에 정률제로 바꾸게되면 이 코드만 바꾸면 된다.
    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
