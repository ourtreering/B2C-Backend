package com.sillock.domain.sillog.model.component;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.dto.SillogDetailDto;
import com.sillock.domain.sillog.model.dto.SillogElementDto;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.dto.SillogTitleDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.model.entity.SillogTitle;
import com.sillock.domain.tag.model.mapper.TagMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = TagMapper.class)
public interface SillogMapper {

    @Mapping(target = "memberId", expression = "java(member.getId())")
    Sillog toEntityFromPostDto(SillogPostDto sillogPostDto, @Context Member member);

    SillogElementDto toSillogElementDtoFromEntity(Sillog sillog);

    SillogTitleDto toSillogTitleDtoFromSillogTitle(SillogTitle sillogTitle);

    SillogDetailDto toSillogDetailDtoFromEntity(Sillog sillog);
}
