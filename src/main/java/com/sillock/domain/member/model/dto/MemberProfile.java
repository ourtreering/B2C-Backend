package com.sillock.domain.member.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfile {
    private String email;
    private String nickname;
    private String profileImage;
    private String identifier;
}
