package com.sillock.member.service;

import com.sillock.core.exception.BadRequestException;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Optional<Member> findById(Long memberId){
        if(memberRepository.findById(memberId).isEmpty())
            throw new BadRequestException("존재하지 않은 사용자에 대한 요청입니다.");
        return memberRepository.findById(memberId);
    }

    @Transactional
    public List<Member> findAll(){
        if(memberRepository.findAll().isEmpty())
            throw new BadRequestException("존재하지 않은 사용자들에 대한 요청입니다.");
        return memberRepository.findAll();
    }
}