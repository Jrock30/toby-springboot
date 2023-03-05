package com.jrock.config.autoconfig;

import com.jrock.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@MyAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    public ServerProperties serverProperties(Environment environment) {
        // 설정된 프로퍼티와 클래스 필드가 이름이 맞으면 자동으로 바인딩을 해준다.
        return Binder.get(environment).bind("", ServerProperties.class).get();
    }

}
