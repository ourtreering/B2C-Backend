package com.sillock.core.jwt;

import com.sillock.member.entity.Member;
import com.sillock.member.jwt.Role;
import com.sillock.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDtailsService implements UserDetailsService {


    @Autowired
    private MemberRepository memberRepository;

    //들어온 email로 Member를 찾아 User객체로 반환
    //UniqueCode -> password로 수정해야함
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        return new User(member.getEmail(), member.getUniqueCode(),grantedAuthorities);
    }


}