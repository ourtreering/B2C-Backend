package com.sillock.member;

import com.sillock.annotation.SillockDataTest;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
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
                .email("test@gmail.com")
                .isActive(true)
                .name("Test")
                .uniqueCode("Test123")
                .regDate(LocalDateTime.of(2021,1,11,15,21,2))
                .modDate(LocalDateTime.of(2021,1,11,15,21,2))
                .build();

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getMemberId()).get();
        assertThat(member.getMemberId()).isEqualTo(result.getMemberId());
    }


    //    @Test
//    @DatabaseSetup(value="classpath:dbunit/entity/member.xml")
//    public void test() {
//    }
}