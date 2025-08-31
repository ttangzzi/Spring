package com.example.demo.beanfind;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // 검증 : sout
        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService.getClass() = " + memberService.getClass());
        // 검증 : Assertions
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        // 검증 : Assertions
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        // 구현에 의존한 것이기 때문에 좋은 코드는 아니지만 이렇게도 가능함을 보여주기 위한 테스트
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        // 검증 : Assertions
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        // ac.getBean("xxxx", MemberService.class);

        // 이 오른쪽 로직이 왼쪽의 예외 터져야 테스트가 성공하는 것
        // assertThrows는 Assertions - junit
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }
}
