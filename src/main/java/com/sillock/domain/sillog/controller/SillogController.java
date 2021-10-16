package com.sillock.domain.sillog.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.core.error.ErrorCode;
import com.sillock.core.error.exception.BusinessException;
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
        String sillogId = sillogService.registerSillog(sillogMapper.toEntityFromPostDto(sillogPostDto, member));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.of(HttpStatus.CREATED, ResponseMessage.REGISTER_SILLOG, sillogId));
    }

    /**
     * TODO
     * 사용자의 권한 체크를 어느 단에서 처리할 것인가
     * 1. 컨트롤러
     * 2. 서비스
     * 3. 리포지토리
     */

    @PatchMapping("/{sillogId}")
    public ResponseEntity<ResponseDto<?>> patchSillog(@CurrentUser Member member,
                @PathVariable ObjectId sillogId, @RequestBody SillogPostDto sillogPostDto){

        Sillog sillog = sillogService.findById(sillogId);

        // Todo : AOP로 처리가능
        if(!sillog.isWriter(member.getId()))
            throw new BusinessException(ErrorCode.MEMBER_NOT_WRITER);

        List<Tag> preTagList = sillog.getTagList();
        sillogMapper.updateSillogEntity(sillogPostDto, sillog);
        sillogService.updateSillog(sillog, preTagList);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.UPDATE_SILLOG));
    }

    @DeleteMapping("/{sillogId}")
    public ResponseEntity<ResponseDto<?>> patchSillog(@CurrentUser Member member,
                                                      @PathVariable ObjectId sillogId){

        Sillog sillog = sillogService.findById(sillogId);

        // Todo : AOP로 처리가능
        if(!sillog.isWriter(member.getId()))
            throw new BusinessException(ErrorCode.MEMBER_NOT_WRITER);

        sillogService.deleteSillog(member.getId(), sillog);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ResponseDto.of(HttpStatus.NO_CONTENT, ResponseMessage.DELETE_SILLOG));
    }

}
