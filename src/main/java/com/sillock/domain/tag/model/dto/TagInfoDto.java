package com.sillock.domain.tag.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 태그 정보들 조회 시 사용
 */
public class TagInfoDto {
    private String category;

    private List<String> tagNameList;
}
