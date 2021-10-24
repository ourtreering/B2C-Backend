package com.sillock.domain.template.model.mapper;

import com.sillock.domain.template.model.dto.TemplateDto;
import com.sillock.domain.template.model.entity.Template;
import com.sillock.domain.template.model.entity.TemplateCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TemplateMapper {
    Map<TemplateCategory, List<TemplateDto>> toDtoMapFromEntityMap(Map<TemplateCategory, List<Template>> templateListMap);

    List<TemplateDto> toDtoListFromEntityList(List<Template> templateList);

    @Mapping(target = "id", expression = "java(template.getId().toString())")
    TemplateDto toDtoFromEntity(Template template);

    @Mapping(target = "id", ignore = true)
    Template toEntityFromTemplateDto(TemplateDto templateDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TemplateDto dto, @MappingTarget Template template);
}
