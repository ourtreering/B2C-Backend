package com.sillock.domain.tag.service;

import com.sillock.core.annotation.PublishEvent;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.model.entity.TagInfo;
import com.sillock.domain.tag.repository.TagInfoRepository;
import com.sillock.domain.tag.repository.TagRepository;
import com.sillock.event.entity.CalculateTagEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagService {
    private final TagInfoRepository tagInfoRepository;
    private final TagRepository tagRepository;

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
}
