package com.sillock.domain.sillog.model.component;

import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.entity.Qna;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QnaMapper {
    QnaDto toDto(Qna qna);
    Qna toEntity(QnaDto qnaDto);
}
