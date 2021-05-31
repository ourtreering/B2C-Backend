package com.sillock.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.core.exception.BadRequestException;
import com.sillock.core.login.SocialProfile;
import com.sillock.core.login.SocialService;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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