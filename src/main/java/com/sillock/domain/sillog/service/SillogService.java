package com.sillock.domain.sillog.service;

import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.SillogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class SillogService {
    @Autowired
    SillogRepository sillogRepository;

    public List<Sillog> getSillogList(){
        Qna qna = Qna.builder()
                .question("첫번째 질문입니다.")
                .answer("첫번째 답변입니다.")
                .tags(Arrays.asList("tag1", "tag2"))
                .build();

        Sillog sillog = Sillog.builder()
                .author("글쓴이")
                .title("제목")
                .sequence(1)
                .qnaData(Arrays.asList(qna))
                .image(Arrays.asList("/src/image"))
                .qualification(Arrays.asList("/src/qualification"))
                .regDate(LocalDate.of(2021, 7, 7))
                .startDate(LocalDate.of(2021, 7, 7))
                .endDate(LocalDate.of(2021, 7, 8))
                .build();

        return Arrays.asList(sillog,sillog);
    }
}