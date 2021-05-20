package com.chitts.config;

import com.chitts.dao.query.EmployeeSqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DataBaseInit {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataBaseInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void createTable() {
        jdbcTemplate.update(EmployeeSqlQuery.CREATE_TABLE);
        jdbcTemplate.update(EmployeeSqlQuery.INSERT);
    }

    @PreDestroy
    private void dropTable() {
        jdbcTemplate.update(EmployeeSqlQuery.DROP_TABLE);
    }
}