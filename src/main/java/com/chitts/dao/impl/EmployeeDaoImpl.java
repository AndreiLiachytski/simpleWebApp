package com.chitts.dao.impl;

import com.chitts.dao.Dao;
import com.chitts.dao.query.EmployeeSqlQuery;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.exceptions.EntityNotFoundException;
import com.chitts.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements Dao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Employee employee) {

        jdbcTemplate.update(EmployeeSqlQuery.SAVE, employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getGender().toString(), employee.getDateOfBirth());
    }

    @Override
    public List<DtoEmployeeShort> getAll() {

        return jdbcTemplate.query(EmployeeSqlQuery.GET_ALL, new BeanPropertyRowMapper<>(DtoEmployeeShort.class));
    }

    @Override
    public DtoEmployeeFull getById(final long id) throws EntityNotFoundException {

        return jdbcTemplate.query(EmployeeSqlQuery.GET_BY_ID, new BeanPropertyRowMapper<>(DtoEmployeeFull.class), id)
                .stream().findAny().orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void update(final long id, Employee employee) {
        jdbcTemplate.update(EmployeeSqlQuery.UPDATE, employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getGender().toString(), employee.getDateOfBirth(), id);

    }

    @Override
    public void delete(final long id) {
        jdbcTemplate.update(EmployeeSqlQuery.DELETE, id);
    }
}