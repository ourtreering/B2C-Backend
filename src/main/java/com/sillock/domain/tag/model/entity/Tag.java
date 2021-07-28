package com.sillock.domain.tag.model.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Document(collection = "tag")
public class Tag {
    @Id
    private ObjectId id;

    @Indexed
    private ObjectId memberId;

    private String category;

    private String name;

    @Builder.Default
    private LocalDate regDate = LocalDate.now();

    @Builder.Default
    private LocalDate modDate = LocalDate.now();
}