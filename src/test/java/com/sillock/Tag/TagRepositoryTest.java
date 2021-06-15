package com.sillock.Tag;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.sillock.annotation.SillockDataTest;
import com.sillock.tag.domain.Tag;
import com.sillock.tag.domain.infra.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SillockDataTest
public class TagRepositoryTest {
    @Autowired
    TagRepository tagRepository;

    @Test
    public void 태그저장(){
        Tag tag = Tag.builder()
                .tagId(1L)
                .name("Test_Tag")
                .build();

        tagRepository.save(tag);
    }

    @Test
    @DatabaseSetup(value="classpath:dbunit/entity/tag.xml")
    public void 태그조회(){
        Tag result = tagRepository.findById(2L).get();
        assertThat(result.getTagId()).isEqualTo(2);
        assertThat(result.getName()).isEqualTo("Test_tag");
    }

    @Test
    @DatabaseSetup(value="classpath:dbunit/entity/tag.xml")
    public void 전체_태그_조회(){
        List<Tag> tags = tagRepository.findAll();

        assertThat(tags.size()).isEqualTo(4);

        Tag tag = tags.get(0);

        assertThat(tag.getTagId()).isEqualTo(1);
        assertThat(tag.getName()).isEqualTo("Test_tag2");

    }
}