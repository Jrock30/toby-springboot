package com.jrock.tobyspringboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> applicationClass, String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class); // 생성 된 서블릿 빈 찾아온다.
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class); // 생성 된 디스패처 서블릿 빈 찾아온다.
                dispatcherServlet.setApplicationContext(this); // 스프링 컨테이너 지정(주입) 없어도 ApplicationContextAware.setApplicationContext 가 자동으로 등록은 해준다.

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass); // 빈 오브젝트 등록 ( 만들어줘 )
        applicationContext.refresh(); // 초기화 ( 빈 생성 )
    }

}
