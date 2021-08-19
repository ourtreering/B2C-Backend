package com.sillock.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.common.message.ExceptionMessage;
import com.sillock.core.exception.BadRequestException;
import com.sillock.core.auth.social.model.SocialProfile;
import com.sillock.core.auth.social.service.SocialService;
import com.sillock.core.exception.ResourceNotFoundException;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final SocialService socialService;

    @Transactional(readOnly = true)
    public Member findByMemberId(ObjectId memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new ResourceNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }


    @Transactional(readOnly = true)
    public Member findByMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Member findByIdentifier(String identifier){
        return memberRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    @Transactional
    public Member register(Member member){
        return memberRepository.save(member);
    }

    public void init(Member member){
        member.setIdentifier("identifier");
    }
}