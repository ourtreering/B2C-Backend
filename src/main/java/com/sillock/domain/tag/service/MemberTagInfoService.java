package com.sillock.domain.tag.service;

import com.sillock.domain.tag.model.entity.MemberTagInfo;
import com.sillock.domain.tag.repository.MemberTagInfoRepository;
import com.sillock.domain.tag.repository.TagInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberTagInfoService {
    private final MemberTagInfoRepository memberTagInfoRepository;

    @Transactional
    public void init(ObjectId memberId) {
        Map<String, Map<String, Integer>> tagInfoMap = new HashMap<>();

        MemberTagInfo memberTagInfo = MemberTagInfo.builder()
                .memberId(memberId)
                .tagInfoUsed(tagInfoMap)
                .build();
        memberTagInfoRepository.save(memberTagInfo);
    }
}
