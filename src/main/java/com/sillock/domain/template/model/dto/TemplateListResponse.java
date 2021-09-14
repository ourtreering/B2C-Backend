package com.sillock.domain.template.model.dto;

import com.sillock.domain.template.model.entity.Template;
import com.sillock.domain.template.model.entity.TemplateCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter @Setter
/**
 * 템플릿 리스트를 조회 시 사용
 */
public class TemplateListResponse {
    private Map<TemplateCategory, List<TemplateDto>> templateListMap;
}
