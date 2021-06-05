package com.chitts.dao;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EntityNotFoundException;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;

import java.util.List;

public interface EmployeeDao {

    long save(DtoEmployeeFull employee) throws EmployeeDaoException;

    List<DtoEmployeeShort> getAll() throws EmployeeDaoException;

    DtoEmployeeFull getById(long id) throws EmployeeDaoException, EntityNotFoundException;

    void update(DtoEmployeeFull employee) throws EmployeeDaoException;

    void delete(long id) throws EmployeeDaoException, EntityNotFoundException;

}