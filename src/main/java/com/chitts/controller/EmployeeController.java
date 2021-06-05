package com.chitts.controller;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EntityNotFoundException;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ApiOperation(value = "Get all employees")
    public ResponseEntity<List<DtoEmployeeShort>> getAll() throws EmployeeDaoException {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee by ID")
    public ResponseEntity<DtoEmployeeFull> getById(@ApiParam(value = "ID for searching employee", required = true)
                                                   @PathVariable final long id) throws EmployeeDaoException, EntityNotFoundException {
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create new Employee")
    public ResponseEntity<DtoEmployeeFull> save(@ApiParam(value = "New employee", required = true)
                                                @ModelAttribute @Valid final DtoEmployeeFull employee,
                                                final BindingResult bindingResult) throws EmployeeDaoException, EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            final String errorMessage = "Error in method 'save'. Data is not valid: " + bindingResult.getModel();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        } else {
            final long id = employeeService.save(employee);
            return new ResponseEntity<>(employeeService.getById(id), HttpStatus.CREATED);
        }
    }

    @PutMapping
    @ApiOperation(value = "Update Employee")
    public ResponseEntity<DtoEmployeeFull> update(@ApiParam(value = "Employee for update", required = true)
                                                  @ModelAttribute @Valid final DtoEmployeeFull employee,
                                                  final BindingResult bindingResult) throws EmployeeDaoException, EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            final String errorMessage = "Error in method 'update'. Data is not valid: " + bindingResult.getModel();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        } else {
            employeeService.update(employee);
            final long id = employee.getEmployeeId();
            return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Employee by ID")
    public ResponseEntity<String> delete(@ApiParam(value = "ID for deleting employee", required = true)
                                         @PathVariable final long id) throws EmployeeDaoException, EntityNotFoundException {
        employeeService.delete(id);
        final String errorMessage = "Employee with id = " + id + " was deleted.";
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

}