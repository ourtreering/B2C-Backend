package com.sillock.core.jwt;

import com.sillock.member.dto.MemberDto;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SillockUserDtails implements UserDetails {
    @Autowired
    MemberRepository memberRepository;

    private Member member;

    @Transactional
    public Member signup(MemberDto memberDto) {
        if (memberRepository.findByEmail(memberDto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        LocalDateTime now = LocalDateTime.now();

        Member member = Member.builder()
                .memberId(memberDto.getMemberId())
                .email(memberDto.getEmail())
                .isActive(true)
                .name(memberDto.getName())
                .uniqueCode(memberDto.getUniqueCode())
                .role(Role.USER)
                .regDate(now)
                .modDate(now)
                .build();

        return memberRepository.save(member);
    }

//    @Transactional(readOnly = true)
//    public Optional<Member> getUserWithAuthorities(String membername) {
//        return memberRepository.findOneWithAuthoritiesByname(membername);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Member> getMyMemberWithAuthorities() {
//        return SecurityUtil.getCurrentName().flatMap(memberRepository::findOneWithAuthoritiesByname);
//    }
}