package com.sillock.common.object;

import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;

import java.time.LocalDate;
import java.util.Arrays;

public class BuilderObjects {

    public Qna basicQna(){
        return Qna.builder().question("첫번째 질문입니다.").answer("첫번째 답변입니다.").tags(Arrays.asList("tag1", "tag2")).build();
    }

    public Sillog basicSillog(){
        Qna qna = this.basicQna();
        return Sillog.builder().author("글쓴이").title("제목").sequence(1).qnaData(Arrays.asList(qna))
                .image(Arrays.asList("/src/image")).qualification(Arrays.asList("/src/qualification")).regDate(LocalDate.of(2021, 7, 7))
                .startDate(LocalDate.of(2021, 7, 7)).endDate(LocalDate.of(2021, 7, 8)).build();
    }

    public Qna customQna(String question, String answer, String tag1, String tag2){
        return Qna.builder().question(question).answer(answer).tags(Arrays.asList(tag1, tag2)).build();
    }

    public Sillog customSillog(String author, String title, int sequence){
        Qna qna = this.basicQna();
        return Sillog.builder().author(author).title(title).sequence(sequence).qnaData(Arrays.asList(qna))
                .image(Arrays.asList("/src/image")).qualification(Arrays.asList("/src/qualification")).regDate(LocalDate.of(2021, 7, 7))
                .startDate(LocalDate.of(2021, 7, 7)).endDate(LocalDate.of(2021, 7, 8)).build();
    }
}