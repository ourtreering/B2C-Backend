package com.sillock.core.aop;

import com.sillock.core.annotation.PublishEvent;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.event.entity.CalculateTagEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


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
        if(method.equals("saveTagList")){
            CalculateTagEvent event = (CalculateTagEvent) returnObj;
            eventPublisher.publishEvent(event);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
