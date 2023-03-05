package com.jrock.config.autoconfig;

import com.jrock.config.ConditionalMyOnClass;
import com.jrock.config.EnableMyConfigurationProperties;
import com.jrock.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
// @Configuration 을 붙여도 되나 관례로 설정 된 어노테이션을 붙인다 , 메타 어노테이션(MyAutoConfiguration)을 가지고 있는 클래스에 @Conditional 사용
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat") // 해당 클래스가 있는지 여부 확인 ConditionalOnClass 가 존재함 이 것은 커스텀
@EnableMyConfigurationProperties(ServerProperties.class) // @Import 를 사용해도 됨 ( 어노테이션으로 뺀 것은 스프링부트 스타일 )
public class TomcatWebServerConfig {
    @Bean("tomcatWebServerFactory")
    // 자동구성 되는 정보 보다 직접 등록한 빈이 먼저 등록 되고 자동 구성이 등록 된다. 따라서 자동 구성에서는 이미 등록 되어있는 빈이 있다면 등록이 안되게 끔
    /**
     * 메서드 레벨에서 컨디션 등록,
     * 스프링부트에 등록되어 있는 빈으로 사용,
     * ServletWebServerFactory 가 사용자가 등록한 빈이 있다면 아래를 등록하지 않음 (구성정보 등록 x)
     * 순서를 항상
     */
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(properties.getContextPath());
        factory.setPort(properties.getPort());
        return factory;
    }

}
