package com.sillock.domain.sillog.model.component;

import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SillogMapper {
    SillogDto toDto(Sillog sillog);

    Sillog toEntity(SillogDto sillogDto);
}
