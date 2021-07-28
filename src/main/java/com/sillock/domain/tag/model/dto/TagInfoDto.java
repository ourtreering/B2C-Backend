package com.sillock.domain.tag.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagInfoDto {
    private String category;

    private List<String> tagNameList;
}
