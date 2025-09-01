package com.hello.core;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    // 테스트 실행 전 무조건 실행되는 것
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given : 이런 환경이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when : 이렇게 하면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then : 이렇게 된다.
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
