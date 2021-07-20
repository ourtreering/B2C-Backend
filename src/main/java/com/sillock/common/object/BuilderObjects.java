package com.sillock.common.object;

import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;

import java.time.LocalDate;
import java.util.Arrays;

public class BuilderObjects {

    public Qna basicQna(){
        return Qna.builder().id("1").question("첫번째 질문입니다.").answer("첫번째 답변입니다.").build();
    }

    public Sillog basicSillog(){
        Qna qna = this.basicQna();
        return Sillog.builder().memberId("1").author("글쓴이").title("제목").qnaData(Arrays.asList(qna))
                .image(Arrays.asList("/src/image")).regDate(LocalDate.of(2021, 7, 7))
               .build();
    }

    public Qna customQna(String question, String answer, String tag1, String tag2){
        return Qna.builder().question(question).answer(answer).build();
    }

    public Sillog customSillog(String memberId, String title){
        Qna qna = this.basicQna();
        return Sillog.builder().memberId(memberId).author("sillog").title(title).qnaData(Arrays.asList(qna))
                .image(Arrays.asList("/src/image")).regDate(LocalDate.of(2021, 7, 7))
                .build();
    }
}