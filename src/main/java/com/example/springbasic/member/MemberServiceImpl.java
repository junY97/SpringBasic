package com.example.springbasic.member;

/**
 * @author junyeong.jo .
 * @since 2023-06-08
 */
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
