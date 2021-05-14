package com.sillock.member.service;

import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    public MemberRepository memberRepository;

    public Optional<Member> findbymemberId(Long memberId){
        Optional<Member> member = memberRepository.findBymemberId(memberId);
        return member;
    }
}