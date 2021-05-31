package com.chitts.dao;

import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.exception.AppException;
import com.chitts.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void save(Employee employee);

    List<DtoEmployeeShort> getAll();

    DtoEmployeeFull getById(long id) throws AppException;

    void update(long id, DtoEmployeeFull employee);

    void delete(long id);

}