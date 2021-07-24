package com.sillock.domain.sillog.model.component;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.dto.SillogResponseDto;
import com.sillock.domain.sillog.model.dto.TagDto;
import com.sillock.domain.sillog.model.entity.Tag;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(target = "memberId", expression = "java(member.getId())")
    Tag toEntityFromSillogPostDto(TagDto tagDto, @Context Member member);
}
