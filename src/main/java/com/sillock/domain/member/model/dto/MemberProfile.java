package com.sillock.domain.member.model.dto;

import lombok.*;
import org.bson.types.ObjectId;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfile {
    private String id;
    private String email;
    private String nickname;
    private String profileImage;
    private String identifier;
}
