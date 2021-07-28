package com.sillock.domain.tag.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.service.SillogService;
import com.sillock.domain.tag.model.dto.TagDto;
import com.sillock.domain.tag.model.dto.TagInfoDto;
import com.sillock.domain.tag.model.mapper.TagMapper;
import com.sillock.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping("/default")
    public ResponseEntity<ResponseDto<List<TagInfoDto>>> getDefaultTagList(@CurrentUser Member member) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_TAG_INFO_LIST,
                        tagService.getDefaultTagList().stream()
                        .map(tagMapper::toTagInfoDtoFromTagInfoEntity)
                        .collect(Collectors.toList())
                ));
    }
    }

