package com.jrock.tobyspringboot;

import com.jrock.config.MySpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@MySpringBootApplication
public class TobySpringbootApplication {

    private final JdbcTemplate jdbcTemplate;

    public TobySpringbootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    public static void main(String[] args) {
//        MySpringApplication.run(TobySpringbootApplication.class, args);
        SpringApplication.run(TobySpringbootApplication.class, args);
    }

}
