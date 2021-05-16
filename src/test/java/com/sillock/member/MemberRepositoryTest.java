package com.sillock.member;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.sillock.annotation.SillockDataTest;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

@ContextConfiguration(classes = MemberRepositoryTest.class,loader = AnnotationConfigWebContextLoader.class)
@SillockDataTest
//@RunWith(SpringRunner.class)
//@ContextConfiguration("classpath:dbunit/entity/member.xml")
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class MemberRepositoryTest {

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
    }

    //    @Test
//    @DatabaseSetup(value="classpath:dbunit/entity/member.xml")
//    public void test() {
//    }
}