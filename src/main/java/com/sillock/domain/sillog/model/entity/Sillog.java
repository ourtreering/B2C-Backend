package com.sillock.domain.sillog.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "sillogs")
public class Sillog {

    private Long id;
    private String author;
    private int sequence;
    private List<Qna> data;
    private List<String> image;
    private List<String> qualification;

}

/**
 * {
 *   template: “프로젝트”,
 *   author: “sillog”,
 *   sequence: 1, (이어쓰기면 2,3,...)
 *   data: [
 *     {
 *     questionId: 1,
 *     question: “첫번째 질문입니다.”,
 *     answer: “첫번째 질문에 대한 대답”,
 *     tags:[“AI”, “교육”]
 *     },
 *     {
 *     questionId: 2,
 *     question: “두번째 질문입니다.”,
 *     answer: “두번째 질문에 대한 대답”,
 *     tags:[“블록체인”]
 *     },
 *   ],
 *   img: [“/src/img/1.jpg”],
 *   qualification: [“/src/qualification/1.pdf”],
 *   mainTags:[“경험”, “...”] }// 연관실록은 현재는 제외된 상태, 일단 무시
 */