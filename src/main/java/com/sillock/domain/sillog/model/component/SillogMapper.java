package com.sillock.domain.sillog.model.component;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.dto.SillogResponseDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = TagMapper.class)
public interface SillogMapper {

    @Mapping(target = "memberId", expression = "java(member.getId())")
    Sillog toEntityFromPostDto(SillogPostDto sillogPostDto, @Context Member member);

}
