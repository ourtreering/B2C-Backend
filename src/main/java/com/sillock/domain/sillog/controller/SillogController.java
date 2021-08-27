package com.sillock.domain.sillog.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogPostDto;

import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.service.SillogService;
import com.sillock.domain.tag.model.entity.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sillogs")
public class SillogController {
    private final SillogService sillogService;
    private final SillogMapper sillogMapper;

    @PostMapping
    public ResponseEntity<ResponseDto<?>> register(@CurrentUser Member member, @RequestBody SillogPostDto sillogPostDto) {
        sillogService.registerSillog(sillogMapper.toEntityFromPostDto(sillogPostDto, member));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.of(HttpStatus.CREATED, ResponseMessage.REGISTER_SILLOG));
    }

    @PatchMapping("/{sillogId}")
    public ResponseEntity<ResponseDto<?>> patchSillog(@CurrentUser Member member,
                @PathVariable ObjectId sillogId, @RequestBody SillogPostDto sillogPostDto){

        Sillog sillog = sillogService.findById(sillogId);
        List<Tag> preTagList = sillog.getTagList();
        sillogMapper.updateSillogEntity(sillogPostDto, sillog);
        sillogService.updateSillog(sillog, preTagList);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.of(HttpStatus.CREATED, ResponseMessage.REGISTER_SILLOG));
    }

}
