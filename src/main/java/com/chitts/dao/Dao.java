package com.chitts.dao;

import com.chitts.exception.AppException;
import com.chitts.models.Model;
import com.chitts.models.impl.Employee;

import java.util.List;

public interface Dao<T extends Model> {

    void save(Employee employee) throws AppException;

    List<T> getAll() throws AppException;

    Employee getById(final long id) throws AppException;

    void update(final long id, Employee employee) throws AppException;

    void delete(final long id) throws AppException;
}