package com.sillock.core.auth.jwt;

import com.sillock.common.EntityFactory;
import com.sillock.core.auth.jwt.component.JwtCreator;
import com.sillock.core.auth.jwt.component.JwtResolver;
import com.sillock.core.auth.jwt.component.JwtValidator;
import com.sillock.domain.member.model.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;

@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtResolver jwtResolver;

    @Autowired
    private JwtCreator jwtCreator;

    @Autowired
    private JwtValidator jwtValidator;

    private final Member member = EntityFactory.basicMemberEntity();

    @Test
    void Token_생성성공() {
        String jwt = jwtCreator.createAccessToken(member);
        System.out.println(jwt);
        assertTrue(jwtValidator.validateToken(jwt));
    }

//    @Test
//    void Token_생성실패_잘못된_JWT_서명 () {
//        String jwt = jwtCreator.createAccessToken(EntityFactory.basicMemberEntity());
//
//        //잘못된 JWT 서명 test
//        String wrongJwt = jwt+"wrongjwt";
//        jwtValidator.validateToken(wrongJwt);
//    }
//
//    @Test
//    void Token_생성실패_만료된_JWT_토큰() {
//        String jwt = jwtTokenProvider.createToken(name,email);
//        appender = new ListAppender<>();
//        appender.start(); //기록을 시작한다
//        log.addAppender(appender);
//
//        Claims claims = Jwts.parserBuilder().setSigningKey(jwtTokenProvider.getSecret()).build().parseClaimsJws(jwt).getBody();
//        Date now = new Date();
//        Date wrongDate = new Date(claims.getExpiration().getTime()-now.getTime()); //만료시간에 현재시간 빼기
//        claims.setExpiration(wrongDate);
//        String wrongDateJwt = Jwts.builder().setClaims(claims).compact();
//        jwtTokenProvider.validateToken(wrongDateJwt);
//
//        Assertions.assertThat(appender.list)
//                .extracting(ILoggingEvent::getFormattedMessage)
//                .containsExactly(JwtMessage.EXPIRED_JWT);
//    }
//
//    @Test
//    void Token_생성실패_지원되지않는_JWT_토큰() {
//        String jwt = jwtTokenProvider.createToken(name,email);
//        appender = new ListAppender<>();
//        appender.start(); //기록을 시작한다
//        log.addAppender(appender);
//
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(jwtTokenProvider.getSecret())
//                .build().parseClaimsJws(jwt).getBody();
//
//        //key 암호화과정을 생략
//        String wrongKeyJwt = Jwts.builder().setClaims(claims).compact();
//        jwtTokenProvider.validateToken(wrongKeyJwt);
//
//        Assertions.assertThat(appender.list)
//                .extracting(ILoggingEvent::getFormattedMessage)
//                .containsExactly(JwtMessage.UNSUPPORTED_JWT);
//    }

}