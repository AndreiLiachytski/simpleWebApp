package com.chitts.dao.impl;

import com.chitts.dao.EmployeeDao;
import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EntityNotFoundException;
import com.chitts.dao.query.EmployeeQuery;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(final DtoEmployeeFull employee) throws EmployeeDaoException {
        log.info("Create a new employee: " + employee.toString());
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update
                (
                        connection -> {
                            PreparedStatement ps = connection.prepareStatement(
                                    EmployeeQuery.SAVE,
                                    new String[]{"employee_id"});
                            ps.setString(1, employee.getFirstName());
                            ps.setString(2, employee.getLastName());
                            ps.setLong(3, employee.getDepartmentId());
                            ps.setString(4, employee.getJobTitle());
                            ps.setString(5, employee.getGender().toString());
                            ps.setDate(6, Date.valueOf(employee.getDateOfBirth()));
                            return ps;
                        },
                        keyHolder
                );
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<DtoEmployeeShort> getAll() throws EmployeeDaoException {
        log.info("Get all Employees");
        return jdbcTemplate.query
                (
                        EmployeeQuery.GET_ALL,
                        new BeanPropertyRowMapper<>(DtoEmployeeShort.class)
                );
    }

    @Override
    public DtoEmployeeFull getById(final long id) throws EmployeeDaoException {
        log.info("Find employee by ID: " + id);
        return jdbcTemplate.query
                (
                        EmployeeQuery.GET_BY_ID,
                        new BeanPropertyRowMapper<>(DtoEmployeeFull.class),
                        id
                ).stream()
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void update(final DtoEmployeeFull employee) throws EmployeeDaoException {
        log.info("Update the employee");
        final long id = employee.getEmployeeId();
        jdbcTemplate.update(
                EmployeeQuery.UPDATE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().toString(),
                employee.getDateOfBirth(),
                id);
    }

    @Override
    public void delete(final long id) throws EmployeeDaoException {
        log.info("Delete employee with ID: " + id);
        if (jdbcTemplate.update(EmployeeQuery.DELETE, id) != 1) {
            throw new EntityNotFoundException(id);
        }
    }

}