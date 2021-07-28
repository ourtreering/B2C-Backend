package com.sillock.domain.tag.model.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "tagInfo")
public class TagInfo {
    @Id
    private ObjectId id;

    private String category;

    private List<String> tagNameList;
}
