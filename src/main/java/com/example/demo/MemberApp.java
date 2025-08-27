package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        // 새로운 회원정보를 임의로 만든 것
        Member member = new Member(1L, "memberA", Grade.VIP);

        // 새로 만든 회원정보로 회원가입
        memberService.join(member);

        // 회원가입이 잘 되었는지 회원조회하기
        Member findMember = memberService.findMember(1L);

        // 생성한 member와 조회한 member를 출력해 비교하기
        System.out.println("new Member = "+member.getName());
        System.out.println("find Member = "+findMember.getName());

        // 이 방법은 이렇게 하나하나 확인해야하기 때문에 좋은 방법이 아니다.
        // 그렇기 때문에 JUnit이라는 테스트 프레임워크를 사용하는 것을 권장한다.
    }
}
