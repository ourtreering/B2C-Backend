package com.sillock.core.login;

import com.google.gson.Gson;
import com.sillock.core.exception.AccessNotAllowedException;
import com.sillock.core.exception.BadRequestException;
import com.sillock.core.exception.ResourceNotFoundException;
import com.sillock.core.jwt.JwtTokenProvider;
import com.sillock.member.entity.Member;
import com.sillock.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    KakaoService kakaoService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Value("${spring.social.kakao.url}")
    private String providerUrl;

    //provider에게 response 받아오기
    public ResponseEntity<String> getResponse(String accessToken) {
        // Set header : Content-type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        try {
            // Request profile
            logger.info("--------- ");
            ResponseEntity<String> response = restTemplate.postForEntity(providerUrl, request, String.class);
            logger.info("response.toString() : "+response.toString());
            if (response.getStatusCode() == HttpStatus.OK)
                return response;
        } catch (Exception e) {
            logger.info(">>> "+e.getMessage());
            throw new AccessNotAllowedException();
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
            logger.info(e.getMessage());
            throw new AccessNotAllowedException();
        }
        throw new BadRequestException();
    }

    //새로받아온 유저의 email을 통해 기존 DB에 존재여부 확인
    public String checkMember(String accessToken){
        KakaoProfile kakaoProfile = getKakaoProfile(accessToken);
        Member member = memberRepository.findByEmail(kakaoProfile.getKakaoAccount().getEmail()).orElseThrow(ResourceNotFoundException::new);
        return jwtTokenProvider.createToken(member.getName(),member.getEmail());
    }
}



