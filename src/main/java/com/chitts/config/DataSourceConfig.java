package com.chitts.config;

import com.chitts.dto.DtoEmployeeFull;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Lazy(false)
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/first_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    @PostConstruct
    public void checkDataSource() {
        jdbcTemplate(dataSource()).query
                (
                        "SELECT * FROM employees ORDER BY employee_id LIMIT 10",
                        new BeanPropertyRowMapper<>(DtoEmployeeFull.class)
                );
    }

}