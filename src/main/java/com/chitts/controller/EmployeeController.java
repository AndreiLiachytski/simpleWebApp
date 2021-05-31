package com.chitts.controller;

import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.model.Employee;
import com.chitts.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public List<DtoEmployeeShort> save(@ModelAttribute("employee") final Employee employee) {
        employeeService.save(employee);
        return employeeService.getAll();
    }

    @GetMapping
    public List<DtoEmployeeShort> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public DtoEmployeeFull getById(@PathVariable("id") final Long id) {
        return employeeService.getById(id);
    }

    @PatchMapping("/{id}")
    public List<DtoEmployeeShort> update(@ModelAttribute("employee") final DtoEmployeeFull employee,
                                         @PathVariable("id") final Long id) {
        employeeService.update(id, employee);
        return employeeService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long id) {
        employeeService.delete(id);
    }

}