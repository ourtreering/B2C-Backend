package com.sillock.domain.sillog.model.dto;

import com.sillock.domain.sillog.model.entity.Qna;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SillogDto {

    private String author;

    private String title;

    private int sequence;

    private List<QnaDto> data;

    private List<String> image;

    private List<String> qualification;

    private LocalDate startDate;

    private LocalDate endDate;
}