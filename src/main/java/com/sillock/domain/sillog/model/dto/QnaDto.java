package com.sillock.domain.sillog.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaDto {
    private String question;
    private String answer;
    private List<String> tags;
}