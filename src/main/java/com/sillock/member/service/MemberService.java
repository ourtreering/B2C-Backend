package com.sillock.member.service;

import com.sillock.core.exception.BadRequestException;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Member findById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()->new BadRequestException("존재하지 않은 사용자에 대한 요청입니다."));
    }

    @Transactional
    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}