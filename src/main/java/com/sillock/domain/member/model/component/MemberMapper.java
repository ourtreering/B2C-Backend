package com.sillock.domain.member.model.component;

import com.sillock.domain.member.model.dto.MemberProfile;
import com.sillock.domain.member.model.dto.MemberSignUp;
import com.sillock.domain.member.model.entity.Member;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberMapper {
    @Mapping(target = "id", expression = "java(member.getId().toString())")
    MemberProfile toDtoFromMemberEntity(Member member);

    Member toEntityFromMemberSignUp(MemberSignUp memberSignUp);

    @Mapping(target = "id", ignore = true)
    void updateProfile(MemberProfile memberProfile, @MappingTarget Member member);
}