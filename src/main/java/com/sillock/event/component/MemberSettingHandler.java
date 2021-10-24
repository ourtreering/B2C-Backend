package com.sillock.event.component;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.service.MemberService;
import com.sillock.domain.sillog.service.SillogService;
import com.sillock.domain.tag.service.MemberTagInfoService;
import com.sillock.domain.tag.service.TagService;
import com.sillock.event.entity.MemberEvent;
import com.sillock.event.entity.MemberSettingType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@RequiredArgsConstructor
@Component
public class MemberSettingHandler {

    private final MemberTagInfoService memberTagInfoService;
    private final MemberService memberService;
    private final SillogService sillogService;
    private final TagService tagService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void setting(MemberEvent event){
        if(event.getType().equals(MemberSettingType.INIT)) {
            String email = event.getMember().getEmail();

            Member member = memberService.findMemberByEmail(email);

            memberTagInfoService.init(member.getId());
        } else if(event.getType().equals(MemberSettingType.DELETE)) {
            Member member = event.getMember();


            /**
             * Todo
             * 사용자 관련 엔티티 전부 삭제
             * 1. 실록
             * 2. 태그
             * 3. 태그정보
             */

        }

    }
}
