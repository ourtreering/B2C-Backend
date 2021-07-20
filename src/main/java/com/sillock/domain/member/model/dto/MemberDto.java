package com.sillock.domain.member.model.dto;

import lombok.*;

import java.time.LocalDate;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;
    private String email;
    private String nickName;
    private String password;
    private LocalDate birth;
    private String profileImage;
    private String gender;
}