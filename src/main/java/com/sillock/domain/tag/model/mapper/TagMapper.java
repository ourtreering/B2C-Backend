package com.sillock.domain.tag.model.mapper;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.tag.model.dto.TagDto;
import com.sillock.domain.tag.model.dto.TagInfoDto;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.model.entity.TagInfo;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(target = "memberId", expression = "java(member.getId())")
    Tag toEntityFromTagDto(TagDto tagDto, @Context Member member);

    TagInfoDto toTagInfoDtoFromTagInfoEntity(TagInfo tagInfo);

    TagInfoDto toTagInfoDtoFromMemberTagInfoUsed(String category, List<String> tagNameList);
}
