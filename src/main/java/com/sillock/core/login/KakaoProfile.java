package com.sillock.core.login;

import lombok.*;

@Getter
@Setter
@ToString
public class KakaoProfile {
    private Long id;
    private Properties properties;
    private KakaoAccount kakaoAccount;

    @Getter
    @Setter
    @ToString
    private static class Properties {
        private String nickname;
        private String thumbnail_image;
        private String profile_image;
    }

    @Getter
    @Setter
    @ToString
    private static class KakaoAccount {
        private String email;
        private String age_range;
        private String  birthday;
        private String gender;
    }

}