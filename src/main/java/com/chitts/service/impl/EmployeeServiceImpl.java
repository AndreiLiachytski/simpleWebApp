package com.chitts.service.impl;

import com.chitts.dao.impl.EmployeeDaoImpl;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.exceptions.EntityNotFoundException;
import com.chitts.models.Employee;
import com.chitts.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger("exception");

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
    public DtoEmployeeFull getById(final long id) {
        DtoEmployeeFull dtoEmployeeFull = null;
        try {
            dtoEmployeeFull = employeeDao.getById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return dtoEmployeeFull;
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