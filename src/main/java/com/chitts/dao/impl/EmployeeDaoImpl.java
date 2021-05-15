package com.chitts.dao.impl;

import com.chitts.exception.AppException;
import com.chitts.exception.EmployeeDaoException;
import com.chitts.models.impl.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO - выполняетзапрос в базу данных(БД) (используйте Spring JDBC)
 */

@Component
public class EmployeeDaoImpl implements Dao<Employee> {

    @Autowired
    private Connection connection;

    @Override
    public void save(Employee employee) throws AppException {

        final String sql = "INSERT into employees values (" +
                employee.getEmployeeId() + "," +
                employee.getFirstName() + "," +
                employee.getLastName() + "," +
                employee.getDepartmentId() + "," +
                employee.getJobTitle() + "," +
                employee.getGender() + ", '10/10/1998')";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException throwable) {
            throw new EmployeeDaoException("Check SQL query in Method *Save*", throwable);
        }
    }

    @Override
    public List<Employee> getAll() throws EmployeeDaoException {

        final List<Employee> models = new ArrayList<>();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employees")) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getLong("employeeId"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setDepartmentId(resultSet.getLong("departmentId"));
                employee.setJobTitle(resultSet.getString("jobTitle"));
                employee.setGender(resultSet.getString("gender"));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                models.add(employee);
            }
        } catch (final SQLException throwable) {
            throw new EmployeeDaoException("Check SQL query in GenericDao.class.", throwable);
        }
        return models;
    }

    @Override
    public Employee getById(final long id) throws AppException {

        final Employee employee = new Employee();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employees WHERE employeeid = " + id)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setEmployeeId(resultSet.getLong("employeeId"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setDepartmentId(resultSet.getLong("departmentId"));
                employee.setJobTitle(resultSet.getString("jobTitle"));
                employee.setGender(resultSet.getString("gender"));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
            }
        } catch (final SQLException throwable) {
            throw new EmployeeDaoException("Check SQL query in GenericDao.class.", throwable);
        }
        return employee;
    }

    @Override
    public void update(final long id, Employee employee) throws AppException {

        String sql = " UPDATE employees SET employeeid=" + employee.getEmployeeId() +
                ", firstname=" + employee.getFirstName() +
                ",lastname =" + employee.getLastName() +
                ",departmentid =" + employee.getDepartmentId() +
                ", jobtitle =" + employee.getJobTitle() +
                ",gender=" + employee.getGender() +
                ",dateofbirth ='10/15/1987'" +
                " WHERE employeeid=" + id;
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException throwable) {
            throw new EmployeeDaoException("Check SQL query in Method *Save*", throwable);
        }
    }

    @Override
    public void delete(final long id) throws AppException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM employees WHERE  employeeid = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new EmployeeDaoException("Check SQL in Method *delete*", throwable);
        }
    }
}