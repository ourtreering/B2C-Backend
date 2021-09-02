package com.sillock.event.component;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.service.MemberService;
import com.sillock.domain.tag.service.MemberTagInfoService;
import com.sillock.event.entity.MemberEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@RequiredArgsConstructor
@Component
public class MemberInitHandler {

    private final MemberTagInfoService memberTagInfoService;
    private final MemberService memberService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void calculate(MemberEvent event){
        String email = event.getMember().getEmail();

        Member member = memberService.findMemberByEmail(email);

        memberTagInfoService.init(member.getId());
    }
}
