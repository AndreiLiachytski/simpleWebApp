package com.chitts.controller;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;
import com.chitts.service.EmployeeService;
import com.chitts.service.dto.employee.EmployeeFullDto;
import com.chitts.service.dto.employee.EmployeeShortDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @ApiOperation(value = "Get all employees")
    public List<EmployeeShortDto> getAll() throws EmployeeDaoException {
        //sssss
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee by ID")
    public EmployeeFullDto getById(@ApiParam(value = "ID for searching employee", required = true)
                                   @PathVariable final long id) throws EmployeeDaoException, EmployeeNotFoundException {
        return employeeService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create new Employee")
    @ResponseStatus(HttpStatus.CREATED)
    public long save(@ApiParam(value = "New employee", required = true)
                     @RequestBody @Valid final EmployeeFullDto employee) throws EmployeeDaoException {
        return employeeService.save(employee);
    }

    @PutMapping
    @ApiOperation(value = "Update Employee")
    public void update(@ApiParam(value = "Employee for update", required = true)
                       @ModelAttribute @Valid final EmployeeFullDto employee) throws EmployeeDaoException, EmployeeNotFoundException {
        employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Employee by ID")
    public long delete(@ApiParam(value = "ID for deleting employee", required = true)
                       @PathVariable final long id) throws EmployeeDaoException, EmployeeNotFoundException {
        return employeeService.delete(id);
    }

}