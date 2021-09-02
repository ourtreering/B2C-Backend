package com.sillock.core.annotation;

import com.sillock.event.entity.MemberSettingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberSetting {
    MemberSettingType type() default MemberSettingType.INIT;
}