package com.sillock.core.login;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialProfile {
    private String email;
    private String gender;
    private String age_range;
}