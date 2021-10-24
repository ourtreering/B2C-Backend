package com.sillock.common;

import com.sillock.domain.member.model.dto.MemberProfile;
import com.sillock.domain.member.model.dto.MemberSignUp;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.tag.model.dto.TagDto;
import com.sillock.domain.tag.model.dto.TagInfoDto;

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

    public static TagInfoDto tagInfoDto() {
        return TagInfoDto.builder()
                .category("test category")
                .tagNameList(Arrays.asList("tag1", "tag2"))
                .build();
    }

    public static MemberSignUp memberSignUpDto(){
        return MemberSignUp.builder()
                .email("test@gmail.com")
                .birth(LocalDate.of(2021,07,21))
                .gender("male")
                .nickname("test")
                .password("1234")
                .profileImage("/test.jpg")
                .build();
    }

    public static MemberProfile memberProfileDto(){
        return memberProfileDto().builder()
                .email("test@gmail.com")
                .identifier("identifier")
                .nickname("test")
                .profileImage("/test.jpg")
                .build();
    }

}
