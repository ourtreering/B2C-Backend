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
public class TemplateDto {
    private TemplateCategory category;

    private List<Qna> qnaList;
}
