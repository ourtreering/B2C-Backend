package com.sillock.domain.sillog.model.component;

import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = QnaMapper.class)
public interface SillogMapper {
    SillogDto toDto(Sillog sillog);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modDate", ignore = true)
    @Mapping(target = "memberId", ignore = true)
    Sillog toEntity(SillogDto sillogDto);
}
