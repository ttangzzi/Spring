package com.hello.core.autowired;

import com.hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutoWiredTest() {
        // TestBean을 스프링 빈 등록하는 용도
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    // Member는 스프링 빈이 아니다.
    // 이 3가지 사례는 각자 스프링 빈 없이도 동작하는 것을 나타낸 것이다.
    static class TestBean {

        // 자동 주입 대상이 없으면 수정자 메서드 자체가 호출안된다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 자동 주입 대상이 없으면 null
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 자동 주입 대상이 없으면 Optional.empty
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
