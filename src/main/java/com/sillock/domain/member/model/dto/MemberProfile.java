package com.sillock.domain.member.model.dto;

import lombok.*;
import org.bson.types.ObjectId;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 사용자 프로필을 조회할 때 및 기본적으로 제공할 때 사용
 */
public class MemberProfile {
    private String id;
    private String email;
    private String nickname;
    private String profileImage;
    private String identifier;
}
