package com.sillock.domain.member.service;

import com.sillock.common.message.ExceptionMessage;
import com.sillock.core.annotation.MemberInit;
import com.sillock.core.auth.social.service.SocialService;
import com.sillock.core.error.ResourceNotFoundException;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberRepository;
import com.sillock.domain.tag.model.entity.TagInfo;
import com.sillock.domain.tag.repository.MemberTagInfoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberTagInfoRepository memberTagInfoRepository;

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
    public Member findByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    @MemberInit
    @Transactional
    public Member register(Member member){
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateProfile(Member member){
        return memberRepository.save(member);
    }

    public void init(Member member){
        member.setIdentifier("identifier");
    }
}