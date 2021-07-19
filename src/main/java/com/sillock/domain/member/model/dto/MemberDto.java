package com.sillock.domain.member.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long memberId;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min=1, max=15)
    private String nickName;

    @NotNull
    @Size(min=4, max=20)
    private String password;

    private LocalDate birth;

    private String profileImage;

    private String gender;

}