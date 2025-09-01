package com.hello.core.member;

public interface MemberRepository {

    // 회원을 저장하는 기능
    void save(Member member);

    // 회원의 Id로 회원을 찾는 기능
    Member findById(Long memberId);
}
