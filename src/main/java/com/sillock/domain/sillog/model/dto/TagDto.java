package com.sillock.domain.sillog.model.dto;

import lombok.*;
import org.bson.types.ObjectId;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private String name;
    private String category;
}