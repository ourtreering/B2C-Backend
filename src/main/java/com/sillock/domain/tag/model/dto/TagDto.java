package com.sillock.domain.tag.model.dto;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private String category;
    private String name;
}