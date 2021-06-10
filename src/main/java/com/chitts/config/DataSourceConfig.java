package com.chitts.config;

import com.chitts.service.dto.employee.EmployeeFullDto;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        final HikariConfig hikariConfig = new HikariConfig("/application.properties");
        return new HikariDataSource(hikariConfig);
    }

    @PostConstruct
    public void checkDataSource() {
        jdbcTemplate(dataSource()).query(
                "SELECT * FROM employees ORDER BY employee_id LIMIT 1",
                new BeanPropertyRowMapper<>(EmployeeFullDto.class));
    }

}