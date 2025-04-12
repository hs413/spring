package com.example.demo2.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
