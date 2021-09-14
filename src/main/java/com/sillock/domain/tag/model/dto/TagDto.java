package com.sillock.domain.tag.model.dto;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 개별 태그 정보가 필요할 때 사용
 */
public class TagDto {
    private String category;
    private String name;
}