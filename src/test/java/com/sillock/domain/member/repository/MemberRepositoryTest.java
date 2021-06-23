package com.sillock.domain.member.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.sillock.annotation.SillockDataTest;
import com.sillock.core.auth.jwt.Role;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SillockDataTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 멤버_회원가입(){
        Member member = Member.builder()
                .memberId(1L)
                .identifier("kakao:123")
                .email("test@gmail.com")
                .isActive(true)
                .name("Test")
                .uniqueCode("Test123")
                .role(Role.USER)
                .regDate(LocalDateTime.of(2021,1,11,15,21,2))
                .modDate(LocalDateTime.of(2021,1,11,15,21,2))
                .build();

        memberRepository.save(member);
    }

    @Test
    @DatabaseSetup(value="classpath:dbunit/entity/member.xml")
    public void 멤버_조회(){
        Member result = memberRepository.findById(1L).get();
        assertThat(result.getMemberId()).isEqualTo(1);
        assertThat(result.getIdentifier()).isEqualTo("kakao:123");
        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
        assertThat(result.getIsActive()).isEqualTo(true);
        assertThat(result.getName()).isEqualTo("Test");
        assertThat(result.getUniqueCode()).isEqualTo("as12sas");
        assertThat(result.getRole().getValue()).isEqualTo("ROLE_USER");
    }


}