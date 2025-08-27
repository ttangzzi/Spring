package com.example.demo.member;

public class MemberServiceImpl implements MemberService{

    // 구현체 선택 : MemoryMemberRepository
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
}
