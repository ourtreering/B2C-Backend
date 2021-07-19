package com.sillock.domain.sillog.model.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Todo
 * 1. Id에 Auto Increment 를 적용해야하나?
 * 2. sillog 에 대한 tag 설정
 * 3. qna list property 이름
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Document(collection = "sillog")
public class Sillog {
    @Id
    private String id;

    private String author;

    private String title;

    private String nextSequence;

    private String previousSequence;

    @DBRef
    private List<Qna> qnaData;

    @DBRef
    private List<Tag> tagData;

    private List<String> image;

    private List<String> file;

    private List<LocalDate> dateList;

    private String memo;

    @Indexed
    @Builder.Default
    private LocalDate regDate = LocalDate.now();

    @Builder.Default
    private LocalDate modDate = LocalDate.now();
}

/**
 * { 
 *   template: “프로젝트”,
 *   author: “sillog”,
 *   title: “뒤질래”,
 *   sequence: 1, (이어쓰기면 2,3,...)
 *   data: [
 *     {
 *     questionId: 1,
 *     question: “첫번째 질문입니다.”,
 *     answer: “첫번째 질문에 대한 대답”,
 *     tags:[ “소통”],
 *     },
 *     {
 *     questionId: 2,
 *     question: “두번째 질문입니다.”,
 *     answer: “두번째 질문에 대한 대답”,
 *     tags:[“융합”],
 *     },
 *   ],
 *   img: “/src/img/1.jpg”,
 *   qualification: “/src/qualification/1.pdf”,
 *   category :[“경험”, “...”],
 *   tags: [],
 *   startDate: 2021-07-05,
 *   endDate: 2021-07-10 }// 연관실록은 현재는 제외된 상태
 */