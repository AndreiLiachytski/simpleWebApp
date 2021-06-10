package com.chitts.dao;

import com.chitts.dao.entity.Employee;
import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeDao {

    long save(Employee employee) throws EmployeeDaoException;

    List<Employee> getAll() throws EmployeeDaoException;

    Employee getById(long id) throws EmployeeDaoException, EmployeeNotFoundException;

    void update(Employee employee) throws EmployeeDaoException, EmployeeNotFoundException;

    long delete(long id) throws EmployeeDaoException, EmployeeNotFoundException;

}