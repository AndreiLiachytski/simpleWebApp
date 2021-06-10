package com.chitts.service;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;
import com.chitts.service.dto.employee.EmployeeFullDto;
import com.chitts.service.dto.employee.EmployeeShortDto;

import java.util.List;

public interface EmployeeService {

    long save(EmployeeFullDto employee) throws EmployeeDaoException;

    List<EmployeeShortDto> getAll() throws EmployeeDaoException;

    EmployeeFullDto getById(long id) throws EmployeeDaoException, EmployeeNotFoundException;

    void update(EmployeeFullDto employee) throws EmployeeDaoException, EmployeeNotFoundException;

    long delete(long id) throws EmployeeDaoException, EmployeeNotFoundException;

}