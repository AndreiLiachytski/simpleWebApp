package com.chitts.dao;

import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.exceptions.AppException;
import com.chitts.models.Employee;

import java.util.List;

public interface Dao {

    void save(Employee employee);

    List<DtoEmployeeShort> getAll();

    DtoEmployeeFull getById(final long id) throws AppException;

    void update(final long id, Employee employee);

    void delete(final long id);
}