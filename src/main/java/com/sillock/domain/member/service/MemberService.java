package com.sillock.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.core.exception.BadRequestException;
import com.sillock.core.auth.social.model.SocialProfile;
import com.sillock.core.auth.social.service.SocialService;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final SocialService socialService;

    @Transactional(readOnly = true)
    public Member findById(ObjectId memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()->new BadRequestException("존재하지 않은 사용자에 대한 요청입니다."));
    }


    @Transactional
    public boolean isExistMemberByProvider(String accessToken, String provider) throws JsonProcessingException {
        SocialProfile profile = socialService.getSocialProfile(accessToken);
        return memberRepository.existsByEmail(profile.getEmail());
    }
}