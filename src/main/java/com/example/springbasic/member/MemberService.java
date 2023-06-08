package com.example.springbasic.member;

/**
 * @author junyeong.jo .
 * @since 2023-06-08
 */
public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
