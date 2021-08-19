package com.sillock.domain.member.model.component;

import com.sillock.domain.member.model.dto.MemberDto;
import com.sillock.domain.member.model.dto.MemberProfile;
import com.sillock.domain.member.model.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberMapper {

    MemberProfile toDtoFromMemberEntity(Member member);
}