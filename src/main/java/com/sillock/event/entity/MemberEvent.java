package com.sillock.event.entity;

import com.sillock.domain.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberEvent {
    private Member member;
    private MemberSettingType type;
}
