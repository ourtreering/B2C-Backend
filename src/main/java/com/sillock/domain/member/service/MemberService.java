package com.sillock.domain.member.service;

import com.sillock.core.annotation.MemberSetting;
import com.sillock.core.error.ErrorCode;
import com.sillock.core.error.exception.EntityNotFoundException;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberMongoTemplate;
import com.sillock.domain.member.repository.MemberRepository;
import com.sillock.event.entity.MemberSettingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMongoTemplate memberMongoTemplate;

    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Member findMemberByEmailWithTemplate(String email) {
        return memberMongoTemplate.findByEmail(email);
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