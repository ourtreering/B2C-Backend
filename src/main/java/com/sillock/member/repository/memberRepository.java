package com.sillock.member.repository;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class memberRepository {
    private Long memberId;

    private String email;

    private Boolean isActive;

    private String name;

    private String uniqueCode;




}