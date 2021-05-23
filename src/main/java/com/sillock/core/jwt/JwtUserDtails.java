//package com.sillock.core.jwt;
//
//import com.sillock.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class JwtUserDtails implements UserDetails {
//
//    private Member member;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
//        auth.add(new SimpleGrantedAuthority(AUTHORITY));
//        return auth;
//    }
//
//    @Override
//    public String getPassword() {
//        return PASSWORD;
//    }
//
//    @Override
//    public String getUsername() {
//        return ID;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return ENABLED;
//    }
//
//    public String getNAME() {
//        return NAME;
//    }
//
//    public void setNAME(String name) {
//        NAME = name;
//    }
//
//}