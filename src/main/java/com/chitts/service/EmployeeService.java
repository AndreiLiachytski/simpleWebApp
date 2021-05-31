package com.chitts.service;

import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.model.Employee;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);

    List<DtoEmployeeShort> getAll();

    DtoEmployeeFull getById(long id);

    void update(long id, DtoEmployeeFull employee);

    void delete(long id);

}