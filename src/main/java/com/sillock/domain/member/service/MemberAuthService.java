package com.sillock.domain.member.service;

import com.sillock.common.message.ExceptionMessage;
import com.sillock.core.error.BadRequestException;
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
            throw new BadRequestException(ExceptionMessage.ALREADY_SIGN_UP_MEMBER);

        memberService.init(member);

        return memberService.register(member);
    }

    @Transactional(readOnly = true)
    public Member login(String email){
        return memberService.findMemberByEmail(email);
    }
}
