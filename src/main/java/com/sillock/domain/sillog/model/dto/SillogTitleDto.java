package com.sillock.domain.sillog.model.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 실록 등록 과정에서 제목을 검색할 때 사용
 */
public class SillogTitleDto {
    private String title;
    private LocalDate regDate;
}
