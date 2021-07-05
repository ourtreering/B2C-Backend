package com.sillock.domain.sillog.model.entity;

import java.util.List;

public class Qna {
    private Long questionId;
    private String question;
    private String answer;
    private List<String> tags;
}
/**
 *  *     {
 *  *     questionId: 1,
 *  *     question: “첫번째 질문입니다.”,
 *  *     answer: “첫번째 질문에 대한 대답”,
 *  *     tags:[“AI”, “교육”]
 *  *     },
 */
