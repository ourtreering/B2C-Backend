package com.sillock.domain.sillog.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SillogDto {
    private Long memberId;
    private String author;
    private String title;
    private String nextSequence;
    private String previousSequence;
    private List<QnaDto> qnaData;
    private List<TagDto> tagData;
    private List<String> image;
    private List<String> file;
    private List<LocalDateTime> dateList;
    private String memo;
}