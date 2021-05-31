package com.chitts.dao.impl;

import com.chitts.dao.EmployeeDao;
import com.chitts.dao.exception.DaoException;
import com.chitts.dao.exception.EntityNotFoundException;
import com.chitts.dao.query.EmployeeQuery;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final Employee employee) {
        jdbcTemplate.update
                (
                        EmployeeQuery.SAVE,
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartmentId(),
                        employee.getJobTitle(),
                        employee.getGender().toString(),
                        employee.getDateOfBirth()
                );
    }

    @Override
    public List<DtoEmployeeShort> getAll() {
        return jdbcTemplate.query
                (
                        EmployeeQuery.GET_ALL,
                        new BeanPropertyRowMapper<>(DtoEmployeeShort.class)
                );
    }

    @Override
    public DtoEmployeeFull getById(final long id) {
        DtoEmployeeFull dtoEmployeeFull = null;
        try {
            dtoEmployeeFull = jdbcTemplate.query
                    (
                            EmployeeQuery.GET_BY_ID,
                            new BeanPropertyRowMapper<>(DtoEmployeeFull.class),
                            id
                    )
                    .stream()
                    .findAny()
                    .orElseThrow(() -> new EntityNotFoundException(id));
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
        }
        return dtoEmployeeFull;
    }

    @Override
    public void update(final long id, final DtoEmployeeFull employee) {
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
    public void delete(final long id) {
        jdbcTemplate.update(EmployeeQuery.DELETE, id);
    }

}