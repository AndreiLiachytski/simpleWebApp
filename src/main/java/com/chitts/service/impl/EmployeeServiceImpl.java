package com.chitts.service.impl;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.impl.EmployeeDaoImpl;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
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
    public long save(final DtoEmployeeFull employee) throws EmployeeDaoException {
        return employeeDao.save(employee);
    }

    @Override
    public List<DtoEmployeeShort> getAll() throws EmployeeDaoException {
        return employeeDao.getAll();
    }

    @Override
    public DtoEmployeeFull getById(final long id) throws EmployeeDaoException {
        return employeeDao.getById(id);
    }

    @Override
    public void update(final DtoEmployeeFull employee) throws EmployeeDaoException {
        employeeDao.update(employee);
    }

    @Override
    public void delete(final long id) throws EmployeeDaoException {
        employeeDao.delete(id);
    }

}