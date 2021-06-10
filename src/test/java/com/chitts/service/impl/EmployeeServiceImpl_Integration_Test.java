package com.chitts.service.impl;

import com.chitts.config.DataSourceTestConfig;
import com.chitts.dao.entity.Gender;
import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;
import com.chitts.service.dto.employee.EmployeeFullDto;
import com.chitts.service.dto.employee.EmployeeShortDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class})
@WebAppConfiguration
@SqlGroup({
        @Sql(value = "classpath:db/test-data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/clean-up.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
public class EmployeeServiceImpl_Integration_Test {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeServiceImpl_Integration_Test(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    public void delete() throws EmployeeDaoException, EmployeeNotFoundException {
        //given
        final List<EmployeeShortDto> employeesList = employeeService.getAll();
        Assertions.assertNotNull(employeesList);

        //when
        employeeService.delete(3);

        //then
        final List<EmployeeShortDto> employeesListAfterDeleting = employeeService.getAll();
        Assertions.assertNotNull(employeesListAfterDeleting);
        Assertions.assertEquals(employeesListAfterDeleting.size(), employeesList.size() - 1);
    }

    @Test
    public void getAll() throws EmployeeDaoException {
        //given
        final List<EmployeeShortDto> employeeList;

        //when
        employeeList = employeeService.getAll();

        //then
        Assertions.assertNotNull(employeeList);
        Assertions.assertEquals(employeeList.size(), 3);
    }

    @Test
    public void getById() throws EmployeeDaoException, EmployeeNotFoundException {
        //given
        final EmployeeFullDto employee;

        //when
        employee = employeeService.getById(1);

        //then
        Assertions.assertEquals(employee.getFirstName(), "Bob");
        Assertions.assertEquals(employee.getLastName(), "Marley");
        Assertions.assertEquals(employee.getDepartmentId(), 2);
        Assertions.assertEquals(employee.getJobTitle(), "singer");
        Assertions.assertEquals(employee.getGender(), Gender.MALE);
    }

    @Test
    public void save() throws EmployeeDaoException {
        //given
        final List<EmployeeShortDto> employeeListBeforeSaveEmployee = employeeService.getAll();
        final EmployeeFullDto employee = EmployeeFullDto
                .builder()
                .employeeId(4)
                .firstName("Dead")
                .firstName("Pool")
                .departmentId(2)
                .jobTitle("soldier")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.now().minusDays(1))
                .build();

        //when
        employeeService.save(employee);

        //then
        final List<EmployeeShortDto> employeeList = employeeService.getAll();
        Assertions.assertNotNull(employeeList);
        Assertions.assertEquals(employeeList.size(), employeeListBeforeSaveEmployee.size() + 1);
    }

    @Test
    public void update() throws EmployeeDaoException, EmployeeNotFoundException {
        //given
        final int id = 2;
        final EmployeeFullDto employeeForUpdating = employeeService.getById(id);

        //when
        employeeForUpdating.setFirstName("firstName");
        employeeForUpdating.setLastName("lastName");
        employeeService.update(employeeForUpdating);

        //then
        final EmployeeFullDto employeeAfterUpdating = employeeService.getById(id);
        Assertions.assertEquals(employeeAfterUpdating.getFirstName(), "firstName");
        Assertions.assertEquals(employeeAfterUpdating.getLastName(), "lastName");
    }

}