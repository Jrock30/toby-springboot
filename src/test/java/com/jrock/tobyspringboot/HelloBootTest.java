package com.jrock.tobyspringboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class) // 스프링 컨텍스를 이용한 테스트
@ContextConfiguration(classes = TobySpringbootApplication.class) // 모든 빈 정보를 끌어오는
@TestPropertySource("classpath:/application.properties") // 프로퍼티 정보를 읽어 옴
@Transactional
public @interface HelloBootTest {
}
