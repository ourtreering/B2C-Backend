package com.sillock.member.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long memberId;

    @Email
    @NotNull
    private String email;

    private Boolean isActive = true;

    @NotNull
    @Size(min=1, max=15)
    private String name;

    @NotNull
    @Size(min=1, max=7)
    private String uniqueCode;

}