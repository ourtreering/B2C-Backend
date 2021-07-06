package com.sillock.domain.sillog.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.SillogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sillogs")
public class SillogController {

    @PostMapping
    public ResponseDto<SillogDto> register(@RequestBody SillogDto sillogDto){
        QnaDto qnaDto = sillogDto.getData().get(0);

        Qna qna = Qna.builder()
                .question(qnaDto.getQuestion())
                .answer(qnaDto.getAnswer())
                .tags(qnaDto.getTags())
                .build();

        Sillog sillog = Sillog.builder()
                .author(sillogDto.getAuthor())
                .title(sillogDto.getTitle())
                .sequence(sillogDto.getSequence())
                .data(Arrays.asList(qna))
                .image(sillogDto.getImage())
                .qualification(sillogDto.getQualification())
                .startDate(sillogDto.getStartDate())
                .endDate(sillogDto.getEndDate())
                .build();

        log.info(sillog.toString());


        return ResponseDto.of(HttpStatus.CREATED, ResponseMessage.REGISTER_SILLOG, sillogDto);
    }


}
