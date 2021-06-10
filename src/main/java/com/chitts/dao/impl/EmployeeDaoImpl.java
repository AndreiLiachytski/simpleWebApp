package com.chitts.dao.impl;

import com.chitts.dao.EmployeeDao;
import com.chitts.dao.entity.Employee;
import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;
import com.chitts.dao.query.EmployeeQuery;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public long save(final Employee employee) throws EmployeeDaoException {
        try {
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection ->
                    {
                        final PreparedStatement ps = connection.prepareStatement(
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
                    keyHolder);
            return Objects.requireNonNull(keyHolder.getKey()).longValue();
        } catch (final RuntimeException ex) {
            final String errorMassage = "Employee not created";
            throw new EmployeeDaoException(errorMassage, ex);
        }
    }

    @Override
    public List<Employee> getAll() throws EmployeeDaoException {
        try {
            return jdbcTemplate.query(
                    EmployeeQuery.GET_ALL,
                    new BeanPropertyRowMapper<>(Employee.class));
        } catch (final RuntimeException ex) {
            final String errorMessage = "Failed to get all employees";
            throw new EmployeeDaoException(errorMessage, ex);
        }
    }

    @Override
    public Employee getById(final long id) throws EmployeeDaoException, EmployeeNotFoundException {
        try {
            return jdbcTemplate.query(
                    EmployeeQuery.GET_BY_ID,
                    new BeanPropertyRowMapper<>(Employee.class),
                    id).stream()
                    .findAny()
                    .orElseThrow(() -> new EmployeeNotFoundException(id));
        } catch (final RuntimeException ex) {
            final String errorMessage = "Failed to get employee by ID";
            throw new EmployeeDaoException(errorMessage, ex);
        }
    }

    @Override
    public void update(final Employee employee) throws EmployeeDaoException, EmployeeNotFoundException {
        try {
            final int updatingResult = jdbcTemplate.update(
                    EmployeeQuery.UPDATE,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartmentId(),
                    employee.getJobTitle(),
                    employee.getGender().toString(),
                    employee.getDateOfBirth(),
                    employee.getEmployeeId());
            if (updatingResult != 1) {
                throw new EmployeeNotFoundException(employee.getEmployeeId());
            }
        } catch (final RuntimeException ex) {
            final String errorMassage = "Check the employee passed to the method ";
            throw new EmployeeDaoException(errorMassage, ex);
        }
    }

    @Override
    public long delete(final long id) throws EmployeeDaoException, EmployeeNotFoundException {
        try {
            if (jdbcTemplate.update(EmployeeQuery.DELETE, id) != 1) {
                throw new EmployeeNotFoundException(id);
            } else {
                return id;
            }
        } catch (final RuntimeException ex) {
            final String errorMassage = "Failed to deleting employee with ID: " + id;
            throw new EmployeeDaoException(errorMassage, ex);
        }
    }

}