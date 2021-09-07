package com.sillock.domain.member.service;

import com.sillock.core.error.ErrorCode;
import com.sillock.core.error.exception.BusinessException;
import com.sillock.domain.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberAuthService {
    private final MemberService memberService;

    @Transactional
    public Member signup(Member member){
        if(memberService.existsByEmail(member.getEmail()))
            throw new BusinessException(ErrorCode.MEMBER_ALREADY_SIGNED_UP);

        memberService.init(member);

        return memberService.register(member);
    }

    @Transactional(readOnly = true)
    public Member login(String email){
        return memberService.findMemberByEmail(email);
    }
}
