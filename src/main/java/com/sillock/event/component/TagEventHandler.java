package com.sillock.event.component;

import com.sillock.core.annotation.CurrentUser;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.tag.model.entity.MemberTagInfo;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.repository.MemberTagInfoRepository;
import com.sillock.event.entity.CalculateTagEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sillock.common.message.ExceptionMessage.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class TagEventHandler {
    private final MemberTagInfoRepository memberTagInfoRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void calculate(CalculateTagEvent event){
        MemberTagInfo memberTagInfo = memberTagInfoRepository.findMemberTagInfoByMemberId(event.getMemberId()).
                orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));

        Map<String, Map<String, Integer>> tagInfoUsed = memberTagInfo.getTagInfoUsed();

        for(Tag tag : event.getTagList()){
            Map<String, Integer> subMap = tagInfoUsed.get(tag.getCategory());

            if(!tagInfoUsed.containsKey(tag.getCategory())){ // Category가 없는 경우
                tagInfoUsed.put(tag.getCategory(), new HashMap<>(){
                        { put(tag.getName(), 1); }
                    }
                );
            } else if(!subMap.containsKey(tag.getName())){ // TagName이 없는 경우
                tagInfoUsed.get(tag.getCategory()).put(tag.getName(), 1);
            } else {
                subMap.put(tag.getName(), subMap.get(tag.getName()) + 1);
            }
        }

        memberTagInfoRepository.save(memberTagInfo);
    }

}
