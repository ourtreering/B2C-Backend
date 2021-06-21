package com.sillock.core.auth.jwt;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtProvider jwtTokenProvider;

    private ListAppender<ILoggingEvent> appender;
    Logger log = (Logger) LoggerFactory.getLogger(JwtProvider.class);

    String name = "Testtest";
    String email = "test@gmail.com";


    @Test
    void Token_생성성공() {
        String jwt = jwtTokenProvider.createToken(name,email);
        assertTrue(jwtTokenProvider.validateToken(jwt));
    }

    @Test
    void Token_생성실패_잘못된_JWT_서명 () {
        String jwt = jwtTokenProvider.createToken(name,email);
        appender = new ListAppender<>();
        appender.start(); //기록을 시작한다
        log.addAppender(appender);

        //잘못된 JWT 서명 test
        String wrongJwt = jwt+"wrongjwt";
        jwtTokenProvider.validateToken(wrongJwt);

            //원하는 필드를 추출하고(extracting) containExactly로 비교.
            //ILoggingEvent::getFormattedMessage 는 로그 표준형 출력의 의미
        Assertions.assertThat(appender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly("잘못된 JWT 서명입니다.");
    }

    @Test
    void Token_생성실패_만료된_JWT_토큰() {
        String jwt = jwtTokenProvider.createToken(name,email);
        appender = new ListAppender<>();
        appender.start(); //기록을 시작한다
        log.addAppender(appender);

        Claims claims = Jwts.parserBuilder().setSigningKey(jwtTokenProvider.getKey()).build().parseClaimsJws(jwt).getBody();
        Date now = new Date();
        Date wrongDate = new Date(claims.getExpiration().getTime()-now.getTime()); //만료시간에 현재시간 빼기
        claims.setExpiration(wrongDate);
        String wrongDateJwt = Jwts.builder().setClaims(claims).compact();
        jwtTokenProvider.validateToken(wrongDateJwt);

        Assertions.assertThat(appender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly("만료된 JWT 토큰입니다.");
    }

    @Test
    void Token_생성실패_지원되지않는_JWT_토큰() {
        String jwt = jwtTokenProvider.createToken(name,email);
        appender = new ListAppender<>();
        appender.start(); //기록을 시작한다
        log.addAppender(appender);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtTokenProvider.getKey())
                .build().parseClaimsJws(jwt).getBody();

        //key 암호화과정을 생략
        String wrongKeyJwt = Jwts.builder().setClaims(claims).compact();
        jwtTokenProvider.validateToken(wrongKeyJwt);

        Assertions.assertThat(appender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly("지원되지 않는 JWT 토큰입니다.");
    }

    @Test
    void Token_생성실패_잘못된_JWT_토큰() {
        String jwt = jwtTokenProvider.createToken(name,email);
        appender = new ListAppender<>();
        appender.start(); //기록을 시작한다
        log.addAppender(appender);

        //잘못된 토큰 인수 넣어주기
        String wrongToken = "";
        jwtTokenProvider.validateToken(wrongToken);

        Assertions.assertThat(appender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly("JWT 토큰이 잘못되었습니다.");
    }

}