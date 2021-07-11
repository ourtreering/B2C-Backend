package com.sillock.domain.sillog.model.component;

import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.entity.Qna;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QnaMapper {
    QnaDto toDto(Qna qna);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "regDate", ignore = true)
    @Mapping(target = "modDate", ignore = true)
    Qna toEntity(QnaDto qnaDto);
}
