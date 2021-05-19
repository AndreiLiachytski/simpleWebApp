package com.chitts.controllers;

import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.exception.AppException;
import com.chitts.models.Employee;
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
    public List<DtoEmployeeShort> save(@ModelAttribute("employee") final Employee employee) {
        employeeService.save(employee);
        return employeeService.getAll();
    }

    @GetMapping()
    public List<DtoEmployeeShort> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public DtoEmployeeFull getById(@PathVariable("id") final Long id) throws AppException {
        return employeeService.getById(id);
    }

    @PatchMapping("update/{id}")
    public List<DtoEmployeeShort> update(@ModelAttribute("employee") final Employee employee,
                                         @PathVariable("id") final Long id) {
        employeeService.update(id, employee);
        return employeeService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Long id) {
        employeeService.delete(id);
    }
}