package com.sillock.domain.sillog.service;

import com.sillock.common.DtoFactory;
import com.sillock.common.EntityFactory;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.repository.MemberRepository;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.SillogRepository;
import com.sillock.domain.sillog.repository.TagRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class SillogServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SillogRepository sillogRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SillogService sillogService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SillogMapper sillogMapper;

    @Test
    @Disabled
    public void 환경_세팅() {
        Member member = EntityFactory.basicMemberEntity();
        memberRepository.save(member);

        Sillog sillog = EntityFactory.basicSillogMemoEntity();
        sillog.setMemberId(member.getId());

        tagRepository.saveAll(sillog.getTagList());
        sillogRepository.save(sillog);
    }

    @Test
    @Disabled
    public void 등록_테스트() {
        SillogPostDto sillogPostDto = DtoFactory.sillogPostDto();
        sillogPostDto.setMemo(EntityFactory.basicMemoEntity());

        Member member = EntityFactory.basicMemberEntity();

        sillogService.register(
                sillogMapper.toEntityFromPostDto(sillogPostDto, member)
        );
    }

    @Test
    @Disabled
    public void DB_삭제() {
        mongoTemplate.getDb().drop();
    }

}
