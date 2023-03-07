package com.jrock.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@HelloBootTest
public class HelloRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("jrock")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("jrock")).isEqualTo(0);

        helloRepository.increaseCount("jrock");
        assertThat(helloRepository.countOf("jrock")).isEqualTo(1);

        helloRepository.increaseCount("jrock");
        assertThat(helloRepository.countOf("jrock")).isEqualTo(2);
    }
}
