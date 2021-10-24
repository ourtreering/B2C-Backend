package com.sillock.domain.tag.model.mapper;

import com.sillock.common.DtoFactory;
import com.sillock.common.EntityFactory;
import com.sillock.domain.tag.model.dto.TagInfoDto;
import com.sillock.domain.tag.model.entity.TagInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;

    private final TagInfo tagInfo = EntityFactory.basicTagInfoEntity();
    private final TagInfoDto tagInfoDto = DtoFactory.tagInfoDto();

    @Test
    void toEntityFromTagDto() {
    }

    @Test
    void toTagInfoDtoFromTagInfoEntity() {
        TagInfoDto mappedDto = tagMapper.toTagInfoDtoFromTagInfoEntity(tagInfo);
        assertEquals(mappedDto.getCategory(), tagInfo.getCategory());
        assertEquals(mappedDto.getTagNameList().get(0), tagInfo.getTagNameList().get(0));
    }

    @Test
    void toTagInfoDtoFromMemberTagInfoUsed(){
        String category = "category";
        List<String> tagNameList = Arrays.asList("tag1", "tag2", "tag3");

        TagInfoDto mappedDto = tagMapper.toTagInfoDtoFromMemberTagInfoUsed(category, tagNameList);
        assertEquals(mappedDto.getTagNameList().size(), tagNameList.size());
        assertEquals(mappedDto.getCategory(), category);
        assertEquals(mappedDto.getTagNameList().get(0), tagNameList.get(0));
    }
}