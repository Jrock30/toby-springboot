package com.jrock.config;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD}) // Conditional 을 만들 때는 보통 메서드도 포함 함
@Conditional(MyOnClassCondition.class)
public @interface ConditionalMyOnClass { // 커스텀 어노테이션을 만드는 주요한 이유는 컨디션에서 읽어 갈 수 있는 어트리뷰트를 주기 위해서, @ConditionalOnClass 이미 스프링부트에 존재 ConditionalMyOnClass 는 커스텀
    String value();
}
