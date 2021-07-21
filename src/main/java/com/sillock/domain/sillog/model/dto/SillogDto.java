package com.sillock.domain.sillog.model.dto;

import com.sillock.domain.member.model.entity.Member;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SillogDto {
    private Member author;
    private String title;
    private String nextSequence;
    private String previousSequence;
    private List<QnaDto> qnaList;
    private List<TagDto> tagList;
    private List<String> image;
    private List<String> file;
    private List<LocalDate> dateList;
    private String memo;
}