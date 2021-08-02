package com.sillock.event.component;

import com.sillock.common.EntityFactory;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.tag.model.entity.MemberTagInfo;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.repository.MemberTagInfoRepository;
import com.sillock.event.entity.CalculateTagEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TagEventHandlerTest {

    @InjectMocks
    private TagEventHandler tagEventHandler;

    @Mock
    private MemberTagInfoRepository memberTagInfoRepository;


    @Test
    void calculate() {
        Member member = EntityFactory.basicMemberEntity();
        Tag tagA1 = Tag.builder().category("A").name("1").build();
        Tag tagA2 = Tag.builder().category("A").name("2").build();
        Tag tagA22 = Tag.builder().category("A").name("2").build();
        Tag tagB1 = Tag.builder().category("B").name("1").build();

        List<Tag> tagList = Arrays.asList(tagA1, tagA2, tagA22, tagB1);
        CalculateTagEvent event = new CalculateTagEvent(member.getId(), tagList);

        MemberTagInfo memberTagInfo = EntityFactory.basicMemberTagInfoEntity();

        given(memberTagInfoRepository.findMemberTagInfoByMemberId(eq(event.getMemberId()))).willReturn(Optional.of(memberTagInfo));

        tagEventHandler.calculate(event);
    }
}