package com.hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{


    // MemoryMemberRepository를 제거 후 생성자를 만든다.
    private final MemberRepository memberRepository;

    // 생성자를 통해서 memberRepository 구현체가 뭐가 들어갈지 선택하도록 한다.
    // 이렇게하면 DIP 원칙을 지킬 수 있다. 구체적인 것에 대해선 MemberServiceImpl은 모르기 때문이다.
    // 구체적인 것은 밖에서 생성해서 넣어주는 것이다. (AppConfig)

    @Autowired // :: (ac.getBean(MemberRepository.class)와 비슷하게 기능한다고 생각하기
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    @Override
    public void join(Member member) {
        // MemoryMemberRepository에 있는 save가 호출됨
        memberRepository.save(member);
    }

    // 회원조회
    @Override
    public Member findMember(Long memberId) {
        // MemoryMemberRepository에 있는 findById가 호출됨
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
