package com.jrock.config.autoconfig;

import com.jrock.config.ConditionalMyOnClass;
import com.jrock.config.EnableMyConfigurationProperties;
import com.jrock.config.MyAutoConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@EnableTransactionManagement // AOP 기반
public class DataSourceConfig {
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    DataSource hikariDataSource(MyDataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean // 위에 빈이 만들어지지 않았을 경우 이게 만들어짐
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class) // 빈 메서드가 실행이 될 때 스프링 컨테이너 빈 구성정보에 하나의 빈이 등록 되어 있다면 그 때 가져온다.
    @ConditionalOnMissingBean // 위에 빈이 만들어지지 않았을 경우 이게 만들어짐
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class) // 빈 메서드가 실행이 될 때 스프링 컨테이너 빈 구성정보에 하나의 빈이 등록 되어 있다면 그 때 가져온다.
    @ConditionalOnMissingBean // 위에 빈이 만들어지지 않았을 경우 이게 만들어짐
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
