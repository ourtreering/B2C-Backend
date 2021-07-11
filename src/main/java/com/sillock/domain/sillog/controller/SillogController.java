package com.sillock.domain.sillog.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.service.SillogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sillogs")
public class SillogController {
    @Autowired
    private SillogService sillogService;

    @PostMapping
    public ResponseDto<SillogDto> register(@RequestBody SillogDto sillogDto) {
        QnaDto qnaDto = sillogDto.getQnaData().get(0);

        Qna qna = Qna.builder()
                .question(qnaDto.getQuestion())
                .answer(qnaDto.getAnswer())
                .tags(qnaDto.getTags())
                .build();

        Sillog sillog = Sillog.builder()
                .author(sillogDto.getAuthor())
                .title(sillogDto.getTitle())
                .sequence(sillogDto.getSequence())
                .qnaData(Arrays.asList(qna))
                .image(sillogDto.getImage())
                .qualification(sillogDto.getQualification())
                .startDate(sillogDto.getStartDate())
                .endDate(sillogDto.getEndDate())
                .build();

        log.info(sillog.toString());

        return ResponseDto.of(HttpStatus.CREATED, ResponseMessage.REGISTER_SILLOG, sillogDto);
    }

    @GetMapping(value = "/{eventId}")
    @ResponseBody
    public ResponseDto<List<SillogDto>> readSillogList(@PathVariable Long eventId) {

        List<Sillog> sillogList = sillogService.getSillogList();
        List<QnaDto> qnaDtoList = new ArrayList<QnaDto>();
        List<SillogDto> sillogDtoList = new ArrayList<SillogDto> ();

        for (Sillog sillog : sillogList){
            List<Qna> qnaData = sillog.getQnaData();

            for (Qna qna : qnaData) {
                QnaDto qnaDto = QnaDto.builder()
                        .question(qna.getQuestion())
                        .answer(qna.getAnswer())
                        .tags(qna.getTags())
                        .build();
                qnaDtoList.add(qnaDto);
            }

            SillogDto sillogDto = SillogDto.builder()
                    .author(sillog.getAuthor())
                    .title(sillog.getTitle())
                    .sequence(sillog.getSequence())
                    .qnaData(qnaDtoList)
                    .image(sillog.getImage())
                    .qualification(sillog.getQualification())
                    .startDate(sillog.getStartDate())
                    .endDate(sillog.getEndDate())
                    .build();
            sillogDtoList.add(sillogDto);
        }

        return ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_EVENT, sillogDtoList);
    }


}
