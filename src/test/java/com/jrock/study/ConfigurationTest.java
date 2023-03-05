package com.jrock.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationTest {

    @Test
    void configuration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig { // 스프링 컨테이너에서 발생하는 프록시 흉내
        private Common common;

        @Override
        Common common() { // 싱글턴 -> 오브젝트가 있으면 한개로만 사용
            if (this.common == null) this.common = super.common();
            return common;
        }
    }

//    @Configuration(proxyBeanMethods = false) // 프록시를 사용하지 않으므로 자바 코드 그대로 사용 됨 (싱글턴 x, new 로 common 각자 생성 됨) , ex) @EnableScheduling, SchedulingConfiguration.java, @AutoConfiguration (/META-INF/spring)
    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }


    private static class Common {
    }
    // Bean1  <-- Common
    // Bean2  <-- Common
}
