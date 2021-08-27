package com.sillock.event.component;

import com.sillock.domain.tag.model.entity.MemberTagInfo;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.repository.MemberTagInfoRepository;
import com.sillock.event.entity.CalculateTagEvent;
import com.sillock.event.entity.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
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

        if (event.getType().equals(EventType.PLUS))
            plusTagAggregation(memberTagInfo, event);
        else if(event.getType().equals(EventType.MINUS)) {
            minusTagAggregation(memberTagInfo, event);
        } else if(event.getType().equals(EventType.UPDATE)){
            minusTagAggregation(memberTagInfo, event);
            plusTagAggregation(memberTagInfo, event);
        }

        memberTagInfoRepository.save(memberTagInfo);
    }

    private void plusTagAggregation(MemberTagInfo memberTagInfo, CalculateTagEvent event){
        Map<String, Map<String, Integer>> tagInfoUsed = memberTagInfo.getTagInfoUsed();

        for(Tag tag : event.getCountUpTagList()){
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
    }

    private void minusTagAggregation(MemberTagInfo memberTagInfo, CalculateTagEvent event){
        Map<String, Map<String, Integer>> tagInfoUsed = memberTagInfo.getTagInfoUsed();

        for(Tag tag : event.getCountDownTagList()){
            Map<String, Integer> tagInfoUsedByCategory = tagInfoUsed.get(tag.getCategory());
            tagInfoUsedByCategory.computeIfPresent(tag.getName(), (k, v) -> {
                v -= 1;
                if (v == 0) return null;
                return v;
            });

            if(tagInfoUsedByCategory.values().size() == 0)
                tagInfoUsed.remove(tag.getCategory());
        }
    }

}
