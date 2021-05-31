package com.sillock.core.dto;

import lombok.*;


@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}