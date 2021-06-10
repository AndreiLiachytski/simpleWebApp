package com.chitts.service.impl;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;
import com.chitts.dao.impl.EmployeeDaoImpl;
import com.chitts.service.EmployeeService;
import com.chitts.service.dto.employee.EmployeeFullDto;
import com.chitts.service.dto.employee.EmployeeShortDto;
import com.chitts.service.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDaoImpl employeeDao;
    private final EmployeeMapper mapper;

    @Override
    public long save(final EmployeeFullDto employee) throws EmployeeDaoException {
        return employeeDao.save(mapper.map(employee));
    }

    @Override
    public List<EmployeeShortDto> getAll() throws EmployeeDaoException {
        return mapper.map(employeeDao.getAll());
    }

    @Override
    public EmployeeFullDto getById(final long id) throws EmployeeDaoException, EmployeeNotFoundException {
        return mapper.map(employeeDao.getById(id));
    }

    @Override
    public void update(final EmployeeFullDto employee) throws EmployeeDaoException, EmployeeNotFoundException {
        employeeDao.update(mapper.map(employee));
    }

    @Override
    public long delete(final long id) throws EmployeeDaoException, EmployeeNotFoundException {
        return employeeDao.delete(id);
    }

}