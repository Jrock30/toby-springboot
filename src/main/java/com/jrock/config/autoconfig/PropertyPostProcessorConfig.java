package com.jrock.config.autoconfig;

import com.jrock.config.MyAutoConfiguration;
import com.jrock.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import java.util.Map;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {

    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException { // 모든 빈 오브젝트를 만들 때 마다 실행이 됨. , 빈 오프젝트 초기화 후 실

                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);// 해당 어노테이션이 달려있을 때 찾음
                if (annotation == null) return bean;

                Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation); // 모든 어노테이션 어트리뷰트를 가져온다.
                String prefix = (String) attrs.get("prefix"); // prefix 어트리뷰트를 가져

                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
