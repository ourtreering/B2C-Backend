package com.sillock.domain.member.repository;

import com.sillock.annotation.SillockDataTest;

import static org.assertj.core.api.Assertions.assertThat;


@SillockDataTest
public class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 멤버_회원가입(){
//        Member member = Member.builder()
//                .memberId(1L)
//                .email("test@gmail.com")
//                .regDate(LocalDateTime.of(2021,1,11,15,21,2))
//                .modDate(LocalDateTime.of(2021,1,11,15,21,2))
//                .build();
//
//        memberRepository.save(member);
//    }
//
//    @Test
//    @DatabaseSetup(value="classpath:dbunit/entity/member.xml")
//    public void 멤버_조회(){
//        Member result = memberRepository.findById(1L).get();
//        assertThat(result.getMemberId()).isEqualTo(1);
//        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
//    }


}