package com.sillock.domain.template.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.domain.template.model.dto.TemplateDto;
import com.sillock.domain.template.model.dto.TemplateListResponse;
import com.sillock.domain.template.model.mapper.TemplateMapper;
import com.sillock.domain.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/templates")
public class TemplateController {
    private final TemplateService templateService;
    private final TemplateMapper templateMapper;

    @GetMapping
    public ResponseEntity<ResponseDto<TemplateListResponse>> getTemplates(){

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS,
                    new TemplateListResponse(templateMapper.toDtoFromEntity(templateService.getTemplates()))));
    }

    @PostMapping
    public ResponseEntity<ResponseDto<?>> createTemplate(@RequestBody TemplateDto dto){
        templateService.createTemplate(templateMapper.toEntityFromTemplateDto(dto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.of(HttpStatus.CREATED, ResponseMessage.CREATED));
    }
}
