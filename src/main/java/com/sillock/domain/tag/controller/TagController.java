package com.sillock.domain.tag.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.tag.model.dto.TagInfoDto;
import com.sillock.domain.tag.model.mapper.TagMapper;
import com.sillock.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_DEFAULT_TAG_INFO_LIST,
                        tagService.getDefaultTagList().stream()
                        .map(tagMapper::toTagInfoDtoFromTagInfoEntity)
                        .collect(Collectors.toList())
                ));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<List<TagInfoDto>>> getMemberTagList(@CurrentUser Member member) {
        Map<String, Map<String, Integer>> tagInfoMap = tagService.getMemberTagInfo(member.getId());

        List<TagInfoDto> tagInfoDtoList = new LinkedList<>();
        for (String category : tagInfoMap.keySet()) {
            tagInfoDtoList.add(
                    tagMapper.toTagInfoDtoFromMemberTagInfoUsed(
                            category, (List<String>) tagInfoMap.get(category).keySet()
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_MEMBER_TAG_INFO_LIST,
                        tagInfoDtoList
                ));
    }
}

