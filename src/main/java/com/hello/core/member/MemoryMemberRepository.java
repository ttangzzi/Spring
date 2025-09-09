package com.hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    // 회원 정보 저장
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    // 회원 아이디 찾기
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
