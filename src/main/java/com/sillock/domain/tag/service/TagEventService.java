package com.sillock.domain.tag.service;

import com.sillock.domain.tag.model.entity.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagEventService {
    private final ApplicationEventPublisher publisher;

    public void calculateMemberTagInfo(List<Tag> tagList){
        publisher.publishEvent(tagList);
    }
}
