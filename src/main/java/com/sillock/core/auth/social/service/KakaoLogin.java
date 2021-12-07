package com.sillock.core.auth.social.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members/login/kakao")
public class KakaoLogin {
    private static final String CLIENT_ID  = "b6ec9ceb0544a27183b1b3eedafd0944"; //REST API 키
    private static final String REDIRECT_URL = "https://sillog.herokuapp.com/api/v1/members/login/kakao/code";

    @GetMapping("/code") //인가코드 받기
    public String getCode(@RequestParam String code){
        return code;
    }


    @GetMapping("/auth") //토큰받기
    public ResponseEntity<String> auth(@RequestParam String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(generateParam(code), headers);
        return requestAuth(kakaoTokenRequest);
    }


    @PostMapping("/user")
    public String getEmail(@RequestParam String code) {
        OauthToken oauthToken = requestAuthCode(generateAuthCodeRequest(code)).getBody();
        assert oauthToken != null;
        return requestProfile(generateProfileRequest(oauthToken)).getBody();
    }

    private ResponseEntity<String> requestAuth(HttpEntity request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                String.class
        );
    }


    private MultiValueMap<String, String> generateParam(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URL);
        params.add("code", code);
        return params;
    }


    private ResponseEntity<String> requestProfile(HttpEntity request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                request,
                String.class
        );
    }

    private HttpEntity<MultiValueMap<String, String>> generateProfileRequest(OauthToken oauthToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ oauthToken.getAccess_token());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return new HttpEntity<>(headers);
    }

    private ResponseEntity<OauthToken> requestAuthCode(HttpEntity request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                OauthToken.class
        );
    }
    private HttpEntity<MultiValueMap<String, String>> generateAuthCodeRequest(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return new HttpEntity<>(generateParam(code), headers);
    }




}