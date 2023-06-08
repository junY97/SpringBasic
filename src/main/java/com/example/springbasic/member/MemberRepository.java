package com.example.springbasic.member;

/**
 * @author junyeong.jo .
 * @since 2023-06-08
 */
public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
