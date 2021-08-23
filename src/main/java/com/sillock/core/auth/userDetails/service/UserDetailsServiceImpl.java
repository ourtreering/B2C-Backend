package com.sillock.core.auth.userDetails.service;

import com.sillock.core.auth.userDetails.model.UserDetailsImpl;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberService memberService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberService.findByMemberByEmail(email);

        return new UserDetailsImpl(member);
    }
}