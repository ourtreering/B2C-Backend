package com.sillock.domain.sillog.model.dto;

import com.sillock.domain.sillog.model.entity.Memo;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.tag.model.dto.TagDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 실록 등록 시 사용
 */
public class SillogPostDto {
    private String title;
    private List<Qna> qnaList;
    private Memo memo;
    private List<TagDto> tagList;
    private List<String> imageList;
    private List<String> fileList;
    private List<LocalDate> dateList;
}
