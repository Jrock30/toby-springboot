package com.jrock.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration(proxyBeanMethods = false)
public class WebServerConfiguration {
    @Bean // 사용자가 등록한 빈은 자동구성 정보 등록 빈 보다 먼저 등록 된다. @ConditionalOnMissingBean 를 자동 구성정보에 등록해주면 알아서 제외 된다.
    ServletWebServerFactory customWebServerFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setPort(9090);
        return serverFactory;
    }
}
