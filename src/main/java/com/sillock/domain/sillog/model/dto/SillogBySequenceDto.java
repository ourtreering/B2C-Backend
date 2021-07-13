package com.sillock.domain.sillog.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SillogBySequenceDto {
    private String author;

    private String title;

    private int sequence;

    private int maxSequence;

    private List<QnaDto> qnaData;

    private List<String> image;

    private List<String> qualification;

    private LocalDate regDate;

    private LocalDate startDate;

    private LocalDate endDate;
}