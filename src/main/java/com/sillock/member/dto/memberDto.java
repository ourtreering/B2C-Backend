package com.sillock.member.dto;
import lombok.*;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class memberDto {


    private Long memberId;

    @Email
    @NotBlank
    private String email;

    private Boolean isActive = true;

    @NotBlank
    private String name;

    @NotBlank
    private String uniqueCode;

}