package com.sillock.core.aop;

import com.sillock.event.entity.CalculateTagEvent;
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
public class PublishEventAspect implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher eventPublisher;

    @Pointcut("@annotation(com.sillock.core.annotation.PublishEvent)")
    public void eventPointcut() {
    }

    @AfterReturning(pointcut = "eventPointcut()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj){
        String method = joinPoint.getSignature().getName();
        log.info(method + "가 실행됩니다.");
        if(method.equals("saveTagList") || method.equals("updateTagList") || method.equals("countUpTagList")){
            CalculateTagEvent event = (CalculateTagEvent) returnObj;
            eventPublisher.publishEvent(event);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
