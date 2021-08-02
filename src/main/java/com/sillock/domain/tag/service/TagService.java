package com.sillock.domain.tag.service;

import com.sillock.core.annotation.PublishEvent;
import com.sillock.domain.tag.model.entity.MemberTagInfo;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.model.entity.TagInfo;
import com.sillock.domain.tag.repository.MemberTagInfoRepository;
import com.sillock.domain.tag.repository.TagInfoRepository;
import com.sillock.domain.tag.repository.TagRepository;
import com.sillock.event.entity.CalculateTagEvent;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

import static com.sillock.common.message.ExceptionMessage.ENTITY_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagService {
    private final TagInfoRepository tagInfoRepository;
    private final TagRepository tagRepository;
    private final MemberTagInfoRepository memberTagInfoRepository;

    @Transactional(readOnly = true)
    public List<TagInfo> getDefaultTagList(){
        return tagInfoRepository.findAll();
    }

    @PublishEvent
    @Transactional
    public CalculateTagEvent saveTagList(ObjectId memberId, List<Tag> tagList) {
        tagRepository.saveAll(tagList);
        return new CalculateTagEvent(memberId, tagList);
    }

    @Transactional(readOnly = true)
    public Map<String, Map<String, Integer>> getMemberTagInfo(ObjectId memberId){
        MemberTagInfo memberTagInfo = memberTagInfoRepository.findMemberTagInfoByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));

        return memberTagInfo.getTagInfoUsed();
    }


}
