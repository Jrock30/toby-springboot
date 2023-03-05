package com.jrock.config.autoconfig;

import com.jrock.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@MyAutoConfiguration // @Configuration 을 붙여도 되나 관례로 설정 된 어노테이션을 붙인다
public class DispacherServletConfig {
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}
