package com.sillock.domain.template.model.entity;

import com.sillock.domain.sillog.model.entity.Qna;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Document(collection = "template")
public class Template {
    @Id
    private ObjectId id;

    private TemplateCategory category;

    private LinkedList<Qna> qnaList;

    @Indexed
    @Builder.Default
    private LocalDate regDate = LocalDate.now();

    @Builder.Default
    private LocalDate modDate = LocalDate.now();
}

