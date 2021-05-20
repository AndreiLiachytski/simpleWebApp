package com.chitts.config;

import com.chitts.dao.query.EmployeeSqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DataBaseInit {

    private static final Logger log = LoggerFactory.getLogger("info");

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataBaseInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void createTable() {
        jdbcTemplate.update(EmployeeSqlQuery.CREATE_TABLE);
        log.info("Table was created");
        jdbcTemplate.update(EmployeeSqlQuery.INSERT);
    }

    @PreDestroy
    private void dropTable() {
        jdbcTemplate.update(EmployeeSqlQuery.DROP_TABLE);
        log.info("Table was dropped");
    }
}