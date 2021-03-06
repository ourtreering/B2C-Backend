package com.sillock.core.auth.jwt.component;

import com.sillock.domain.member.model.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtCreator {
    private final long ACCESS_TOKEN_VALID_MILISECOND = 1000 * 60 * 60 * 24; // 24 시간

    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(Member member) {
        Claims claims = Jwts.claims().setSubject(member.getEmail());
        claims.put("email", member.getEmail());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_MILISECOND))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}