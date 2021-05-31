package com.chitts.service.impl;

import com.chitts.dao.impl.EmployeeDaoImpl;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.model.Employee;
import com.chitts.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDaoImpl employeeDao;

    public EmployeeServiceImpl(final EmployeeDaoImpl employeeDao) {
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
    public DtoEmployeeFull getById(final long id) {
        return employeeDao.getById(id);
    }

    @Override
    public void update(final long id, final DtoEmployeeFull employee) {
        employeeDao.update(id, employee);
    }

    @Override
    public void delete(final long id) {
        employeeDao.delete(id);
    }

}