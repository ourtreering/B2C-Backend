package com.sillock.domain.sillog.model.entity;

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

    private ObjectId memberId;

    private String name;

    private String category;

    @Indexed
    @Builder.Default
    private LocalDate regDate = LocalDate.now();

    @Builder.Default
    private LocalDate modDate = LocalDate.now();
}