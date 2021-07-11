package com.sillock.domain.sillog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "qna")
public class Qna {
    @Id
    private String id;
    private String question;
    private String answer;
    private List<String> tags;
    private LocalDate regDate;
    private LocalDate modDate;
}
/**
 *  *     {
 *  *     questionId: 1,
 *  *     question: “첫번째 질문입니다.”,
 *  *     answer: “첫번째 질문에 대한 대답”,
 *  *     tags:[“AI”, “교육”]
 *  *     },
 */
