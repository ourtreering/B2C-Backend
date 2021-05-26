package com.sillock.core.login;

import com.google.gson.Gson;
import com.sillock.core.exception.BadRequestException;
import com.sillock.core.jwt.JwtTokenProvider;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final RestTemplate restTemplate;
    private final Environment env;
    private final Gson gson;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Value("${spring.social.kakao.url}")
    private String providerUrl;

    //provider(kakao)에게 response 받아오기
    public ResponseEntity<String> getResponse(String accessToken) {
        // Set header : Content-type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        try {
            // Request profile
            ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty(providerUrl), request, String.class);
            if (response.getStatusCode() == HttpStatus.OK)
                return response;
        } catch (Exception e) {
            throw new BadRequestException();
        }
        throw new BadRequestException();
    }

    //access_token으로 사용자 프로필 받아오기
    public KakaoProfile getKakaoProfile(String accessToken) {
        try {
            ResponseEntity<String> response = getResponse(accessToken);
            if (response.getStatusCode() == HttpStatus.OK)
                return gson.fromJson(response.getBody(), KakaoProfile.class);
        } catch (Exception e) {
            throw new BadRequestException();
        }
        throw new BadRequestException();
    }


    //예외처리 만들어주기
    //KakaoAccount 안에 있는 email 빼와야함
    public String checkMember(String accessToken){
        KakaoProfile kakaoProfile = getKakaoProfile(accessToken);
        Member member = memberRepository.findByEmail(String.valueOf(kakaoProfile.getKakaoAccount())).get();
        return jwtTokenProvider.createToken(member.getName(),member.getEmail());
    }


}



