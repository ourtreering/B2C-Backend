package com.sillock.domain.member.service;

import com.sillock.common.message.ExceptionMessage;
import com.sillock.core.annotation.MemberSetting;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberRepository;
import com.sillock.event.entity.MemberSettingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.MEMBER_ENTITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    @MemberSetting
    @Transactional
    public Member register(Member member){
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateProfile(Member member){
        return memberRepository.save(member);
    }

    @MemberSetting(type = MemberSettingType.DELETE)
    @Transactional
    public void deleteMember(Member member){
        memberRepository.delete(member);
    }

    public void init(Member member){
        member.setIdentifier("identifier");
    }
}