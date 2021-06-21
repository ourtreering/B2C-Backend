package com.sillock.domain.member.model.dto;

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

    private String identifier;

    @Email
    @NotNull
    private String email;

    private Boolean isActive;

    @NotNull
    @Size(min=1, max=15)
    private String name;

    @NotNull
    @Size(min=1, max=7)
    private String uniqueCode;

}