package com.hello.core.order;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    // MemoryMemberRepository를 제거 후 생성자를 만든다.
    private final MemberRepository memberRepository;
    // FixDiscountPolicy를 제거 후 생성자를 만든다.
    private final DiscountPolicy discountPolicy;

    // 마찬가지로 생성자를 통해 구현체를 선택할 수 있도록 한다. (2가지)
    // 구체적인 것은 밖에서 생성해서 넣어주는 것이다. (AppConfig)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
