package com.jrock.config.autoconfig;

import com.jrock.config.ConditionalMyOnClass;
import com.jrock.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MyAutoConfiguration // @Configuration 을 붙여도 되나 관례로 설정 된 어노테이션을 붙인다
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfig {
    @Bean("jettyWebServerFactory") // 자동구성 되는 정보 보다 직접 등록한 빈이 먼저 등록 되고 자동 구성이 등록 된다. 따라서 자동 구성에서는 이미 등록 되어있는 빈이 있다면 등록이 안되게끔
    @ConditionalOnMissingBean // 메서드 레벨에서 컨디션 등록, 스프링부트에 등록되어 있는 빈으로 사용, ServletWebServerFactory 가 사용자가 등록한 빈이 있다면 아래를 등록하지 않음 (구성정보 등록 x)
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }

}
