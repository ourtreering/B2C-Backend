package com.sillock.domain.tag.model.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "memberTagInfo")
public class MemberTagInfo {
    @Id
    private ObjectId id;

    @Indexed
    private ObjectId memberId;

    private Map<String, Map<String, Integer>> tagInfoUsed; // Map<Category, Map<TagName, Count>>
}
