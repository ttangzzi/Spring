package com.hello.core.autowired;

import com.hello.core.AutoAppConfig;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int fixDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        // discountService는 DiscountService class의 인스턴스인가 ? O
        assertThat(discountService).isInstanceOf(DiscountService.class);

        // discountPrice는 10000을 fixDiscount(무조건 1000원) 한 값과 같은가 ?
        assertThat(fixDiscountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        // rateDiscountPrice는 20000을 rateDiscount(10%) 한 값과 같은가 ?
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }

    // 기존걸 손대지않고 새로운걸로 대체하기 : 다시 손보기 힘들어서
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            // fixDiscountPolicy와 rateDiscountPolicy가 Map과 List에 모두 주입됨
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            // discountPolicy는 키가 discountCode 인자인 스프링 빈을 꺼내서 저장된다. (fix 혹은 rate)
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            // 해당 인자(fix 혹은 rate)의 discount 메서드의 값을 반환한다.
            return discountPolicy.discount(member, price);
        }
    }
}
