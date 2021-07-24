package com.sillock.domain.sillog.model.dto;

import com.sillock.domain.member.model.dto.MemberDto;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.entity.Memo;
import com.sillock.domain.sillog.model.entity.Qna;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SillogResponseDto {
    private MemberDto author;
    private String title;
    private List<Qna> qnaList;
    private Memo memo;
    private List<TagDto> tagList;
    private List<String> imageList;
    private List<String> fileList;
    private List<LocalDate> dateList;
}