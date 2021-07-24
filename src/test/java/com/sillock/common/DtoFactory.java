package com.sillock.common;

import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.dto.SillogResponseDto;
import com.sillock.domain.sillog.model.dto.TagDto;
import com.sillock.domain.sillog.model.entity.Memo;
import com.sillock.domain.sillog.model.entity.Sillog;

import java.time.LocalDate;
import java.util.Arrays;

public class DtoFactory {
    public static SillogPostDto sillogPostDto() {
        return SillogPostDto.builder()
                .title("test")
                .tagList(Arrays.asList(tagDto()))
                .imageList(Arrays.asList("/src/image"))
                .fileList(Arrays.asList("/src/file"))
                .dateList(Arrays.asList(LocalDate.of(2021,07,23)))
                .build();
    }

    public static TagDto tagDto() {
        return TagDto.builder()
                .category("category test")
                .name("test tag")
                .build();
    }


//    public static SillogResponseDto() {
//        return
//    }
}
