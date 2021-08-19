package com.sillock.domain.member.model.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUp {
    private String email;
    private String nickname;
    private String password;
    private LocalDate birth;
    private String profileImage;
    private String gender;
}