package com.chitts.controllers;

import com.chitts.exception.AppException;
import com.chitts.exception.GenericDaoException;
import com.chitts.models.impl.Employee;
import com.chitts.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller - это REST контроллер, позволяющий отобразить HTTP запросы
 * на методы обрабатывающие их (для этого используйте Spring Web)
 */

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/post")
    public List<Employee> save(@ModelAttribute("employee") Employee employee) throws AppException {
        employeeService.save(employee);
        return employeeService.getAll();
    }

    @GetMapping()
    public List<Employee> getAllEmployees() throws GenericDaoException {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") Long id) throws AppException {
        return employeeService.getById(id);
    }

    @PatchMapping("update/{id}")
    public List<Employee> update(@ModelAttribute("employee") Employee employee,
                                 @PathVariable("id") Long id) throws AppException {
        employeeService.update(id, employee);
        return employeeService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws AppException {
        employeeService.delete(id);
    }
}