package com.sillock.core.auth.jwt.dto;

import lombok.*;


@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}