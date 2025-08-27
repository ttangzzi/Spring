package com.example.demo;

import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        // 생성자를 통해서 MemoryMemberRepository 구현체를 선택한다.
        // 이것을 생성자를 통해 new instance가 들어간다고 해서 "생성자 주입"이라고 한다.
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        // 생성자 주입을 통해 MemoryMemberRepository와 FixDiscountPolicy 구현체를 선택한다.
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
