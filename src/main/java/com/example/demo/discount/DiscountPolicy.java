package com.example.demo.discount;

import com.example.demo.member.Member;

public interface DiscountPolicy {

    // @return 할인 대상 금액
    // grade만 넘겨도되지만 추후의 확장성을 고려해서 member를 통으로 넘겼다.
    // price 또한 정액제 할인에서는 필요없지만 추후 정률제로 바뀐다면 가격기준 비율로 할인이 되기 때문에 포함한 것
    int discount(Member member, int price);
}
