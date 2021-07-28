package com.sillock.domain.sillog.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.dto.SillogResponseDto;

import com.sillock.domain.sillog.service.SillogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sillogs")
public class SillogController {
    private final SillogService sillogService;
    private final SillogMapper sillogMapper;

    @PostMapping
    public ResponseEntity<ResponseDto<?>> register(@CurrentUser Member member, @RequestBody SillogPostDto sillogPostDto) {
        sillogService.register(sillogMapper.toEntityFromPostDto(sillogPostDto, member));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.of(HttpStatus.CREATED, ResponseMessage.REGISTER_SILLOG));
    }

}
