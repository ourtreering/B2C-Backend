package com.sillock.core.auth.jwt.model;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfile {
    private String id;
    private String email;
}
