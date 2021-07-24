package com.sillock.domain.member.model.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private ObjectId memberId;
    private String email;
    private String nickName;
    private String password;
    private LocalDate birth;
    private String profileImage;
    private String gender;
}