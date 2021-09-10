package com.sillock.domain.sillog.model.dto;

import com.sillock.domain.sillog.model.entity.Memo;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.tag.model.dto.TagDto;
import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SillogElementDto {
    private String id;
    private String title;
    private List<Qna> qnaList;
    private Memo memo;
    private List<TagDto> tagList;
    private List<LocalDate> dateList;
}