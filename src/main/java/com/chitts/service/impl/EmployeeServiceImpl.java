package com.chitts.service.impl;

import com.chitts.dao.impl.EmployeeDaoImpl;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.exception.AppException;
import com.chitts.models.Employee;
import com.chitts.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service - это сервис выполняющий бизнес-логику, в данном задании бизнес-логики практически нет,
 * и сервис просто делает вызов DAO (для DI используйте Spring)
 */

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDaoImpl employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void save(final Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public List<DtoEmployeeShort> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public DtoEmployeeFull getById(final long id) throws AppException {
        return employeeDao.getById(id);
    }

    @Override
    public void update(final long id, final Employee employee) {
        employeeDao.update(id, employee);
    }

    @Override
    public void delete(final long id) {
        employeeDao.delete(id);
    }
}