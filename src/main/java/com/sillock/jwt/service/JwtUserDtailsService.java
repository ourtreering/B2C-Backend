package com.sillock.jwt.service;

import com.sillock.member.entity.Member;
import com.sillock.member.jwt.Role;
import com.sillock.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDtailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    //컨트롤러에서 넘어온 email과 UniqueCode(password)값이 db에 저장된 비밀번호와 일치하는지 검사
    //UniqueCode -> password로 수정해야함
    public Member authenticateByEmailAndPssword(String email, String uniqueCode){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        if(!passwordEncoder.matches(uniqueCode, member.getUniqueCode())) {
            throw new BadCredentialsException("옳지않은 비밀번호입니다.");
        }
        return member;
    }
}