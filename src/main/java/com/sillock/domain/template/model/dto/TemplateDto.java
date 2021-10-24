package com.sillock.domain.template.model.dto;

import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.template.model.entity.TemplateCategory;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 템플릿 생성할 때 사용
 */
public class TemplateDto {

    private String id;
    private TemplateCategory category;
    private List<Qna> qnaList;
}
