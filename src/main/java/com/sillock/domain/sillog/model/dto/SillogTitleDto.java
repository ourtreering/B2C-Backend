package com.sillock.domain.sillog.model.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SillogTitleDto {
    private String title;
    private LocalDate regDate;
}
