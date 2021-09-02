package com.sillock.core.aop;

import com.sillock.core.annotation.MemberSetting;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.event.entity.MemberEvent;
import com.sillock.event.entity.MemberSettingType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MemberAspect implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher eventPublisher;

    @Pointcut("@annotation(memberSetting)")
    public void pointcut(MemberSetting memberSetting) {
    }

    @AfterReturning(pointcut = "pointcut(memberSetting)", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, MemberSetting memberSetting, Object returnObj){
        Member member = null;

        for(Object arg : joinPoint.getArgs()){
            if(arg instanceof Member) {
                member = (Member) arg;
            }
        }

        eventPublisher.publishEvent(new MemberEvent(member, memberSetting.type()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
