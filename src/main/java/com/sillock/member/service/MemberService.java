package com.sillock.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.core.exception.BadRequestException;
import com.sillock.common.login.SocialProfile;
import com.sillock.common.login.SocialService;
import com.sillock.member.domain.entity.Member;
import com.sillock.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final SocialService socialService;

    @Transactional(readOnly = true)
    public Member findById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()->new BadRequestException("존재하지 않은 사용자에 대한 요청입니다."));
    }


    @Transactional
    public boolean isExistMemberByProvider(String accessToken, String provider) throws JsonProcessingException {
        SocialProfile profile = socialService.getSocialProfile(accessToken);
        return memberRepository.existsByEmail(profile.getEmail());
    }
}