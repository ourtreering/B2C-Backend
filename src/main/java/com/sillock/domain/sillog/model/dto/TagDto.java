package com.sillock.domain.sillog.model.dto;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private Long memberId;
    private String name;
    private String category;
}