//package com.sillock.core.login;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class KakaoServiceTest {
//
//    @Autowired
//    private KakaoService kakaoService;
//
//    @Test
//    void checkMember_기존DB유저면_jwt발급() { //엑세스 토큰 발급받아봐야함
//        String accessToken = "aiubgIlaAwd_Dr7oHMrYdgwL6k4akLNJQu9eIgo9dRoAAAF5sPAVoA";
//        // given
//        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
//        // then
//        Assertions.assertNotNull(profile);
//        Assertions.assertEquals(profile.getKakaoAccount().getEmail(), "so19980@naver.com");
//    }
//
//    @Test
//    void 임시_Json객체_response() throws JSONException {
//        String jsonString;
//        jsonString = "{\n" +
//                "                \"id\": 1747815502,\n" +
//                "                \"connected_at\": \"2021-05-28T01:42:11Z\",\n" +
//                "                \"properties\": {\n" +
//                "            \"nickname\": \"정소연\",\n" +
//                "                    \"profile_image\": \"http://k.kakaocdn.net/dn/tcD9X/btq5fx70Hsv/KtTocCf3mf7GkxB2RSFcd1/img_640x640.jpg\",\n" +
//                "                    \"thumbnail_image\": \"http://k.kakaocdn.net/dn/tcD9X/btq5fx70Hsv/KtTocCf3mf7GkxB2RSFcd1/img_110x110.jpg\"\n" +
//                "        },\n" +
//                "        \"kakao_account\": {\n" +
//                "            \"profile_needs_agreement\": false,\n" +
//                "                    \"profile\": {\n" +
//                "                \"nickname\": \"정소연\",\n" +
//                "                        \"thumbnail_image_url\": \"http://k.kakaocdn.net/dn/tcD9X/btq5fx70Hsv/KtTocCf3mf7GkxB2RSFcd1/img_110x110.jpg\",\n" +
//                "                        \"profile_image_url\": \"http://k.kakaocdn.net/dn/tcD9X/btq5fx70Hsv/KtTocCf3mf7GkxB2RSFcd1/img_640x640.jpg\",\n" +
//                "                        \"is_default_image\": false\n" +
//                "            },\n" +
//                "            \"has_email\": true,\n" +
//                "                    \"email_needs_agreement\": false,\n" +
//                "                    \"is_email_valid\": true,\n" +
//                "                    \"is_email_verified\": true,\n" +
//                "                    \"email\": \"so19980@naver.com\",\n" +
//                "                    \"has_age_range\": true,\n" +
//                "                    \"age_range_needs_agreement\": false,\n" +
//                "                    \"age_range\": \"20~29\",\n" +
//                "                    \"has_birthday\": true,\n" +
//                "                    \"birthday_needs_agreement\": false,\n" +
//                "                    \"birthday\": \"0424\",\n" +
//                "                    \"birthday_type\": \"SOLAR\",\n" +
//                "                    \"has_gender\": true,\n" +
//                "                    \"gender_needs_agreement\": false,\n" +
//                "                    \"gender\": \"female\"\n" +
//                "        }\n" +
//                "}";
//
//        JSONObject jObject = new JSONObject(jsonString);
//
//
//
//
//    }
//}
//
//
