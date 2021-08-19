package com.sillock.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.common.message.ExceptionMessage;
import com.sillock.core.exception.BadRequestException;
import com.sillock.core.auth.social.model.SocialProfile;
import com.sillock.core.auth.social.service.SocialService;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final SocialService socialService;

    @Transactional(readOnly = true)
    public Member findByMemberId(ObjectId memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }


    @Transactional(readOnly = true)
    public Member findByMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Member findByIdentifier(String identifier){
        return memberRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }
}