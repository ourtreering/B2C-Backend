package com.sillock.domain.sillog.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogDto;

import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.service.SillogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sillogs")
public class SillogController {
    private final SillogService sillogService;
    private final SillogMapper sillogMapper;


    @PostMapping
    public ResponseDto register(@RequestBody SillogDto sillogDto) {
        Sillog sillog = sillogMapper.toEntity(sillogDto);
        sillogService.register(sillog);
        return ResponseDto.of(HttpStatus.CREATED, ResponseMessage.REGISTER_SILLOG);
    }


}
