package com.sillock.domain.sillog.mapper;

import com.sillock.common.DtoFactory;
import com.sillock.common.EntityFactory;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SillogMapperTest {

    @Autowired
    private SillogMapper sillogMapper;

    private final Member member = EntityFactory.basicMemberEntity();
    private final Sillog sillog = EntityFactory.basicSillogMemoEntity();

    private final SillogPostDto sillogPostDto = DtoFactory.sillogPostDto();

    @Test
    public void toEntityFromPostDto_withMemo() {
        // set Memo
        sillogPostDto.setMemo(EntityFactory.basicMemoEntity());

        Sillog mappedSillog = sillogMapper.toEntityFromPostDto(sillogPostDto, member);

        assertEquals(sillogPostDto.getTitle(), mappedSillog.getTitle());
        assertEquals(sillogPostDto.getMemo().getBody(), mappedSillog.getMemo().getBody());
        assertEquals(sillogPostDto.getDateList(), mappedSillog.getDateList());
        assertEquals(sillogPostDto.getTagList().get(0).getName(), mappedSillog.getTagList().get(0).getName());
        assertEquals(sillogPostDto.getTagList().get(0).getCategory(), mappedSillog.getTagList().get(0).getCategory());
        assertEquals(member.getId(), mappedSillog.getTagList().get(0).getMemberId());
    }
}
