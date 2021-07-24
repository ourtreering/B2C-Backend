package com.sillock.core.auth.jwt;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.sillock.common.message.JwtMessage;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * created by soyeon 21/06/23
 */
@Slf4j
@Component
public class JwtProvider{

    @Value("${jwt.secret}")
    private String secret;
    private long tokenValidityInMilliseconds = 86400000;
    private static final String AUTHORITIES_KEY = "auth";

    public String getSecret(){
        return secret;
    }

    @PostConstruct
    public void initialize(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String name, String email) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("name", name);

        Date now = new Date();
        Date validity = new Date(now.getTime() + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User user = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }


    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String Token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(Token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info(JwtMessage.WRONG_JWT);
        } catch (ExpiredJwtException e) {
            log.info(JwtMessage.EXPIRED_JWT);
        } catch (UnsupportedJwtException e) {
            log.info(JwtMessage.UNSUPPORTED_JWT);
        } catch (IllegalArgumentException e) {
        }
        return false;
    }


}