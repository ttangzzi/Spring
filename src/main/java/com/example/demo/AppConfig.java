package com.example.demo;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderServiceImpl;

// 이제 객체의 생성과 연결은 AppConfig가 담당한다.
public class AppConfig {
    public MemberService memberService() {
        // 생성자를 통해서 MemoryMemberRepository 구현체를 선택한다.
        // 이것을 생성자를 통해 new instance가 들어간다고 해서 "생성자 주입"이라고 한다.
        return new MemberServiceImpl(memberRepository());
    }

    // 리팩토링 : 역할들에 대한 구현을 한 눈에 들어오도록 하기.
    // 나중에 DB 저장소로 바꾸게되면 이 코드만 바꾸면 된다.
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        // 생성자 주입을 통해 MemoryMemberRepository와 FixDiscountPolicy 구현체를 선택한다.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 리팩토링 : 역할들에 대한 구현을 한 눈에 들어오도록 하기.
    // 나중에 정률제로 바꾸게되면 이 코드만 바꾸면 된다.
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
