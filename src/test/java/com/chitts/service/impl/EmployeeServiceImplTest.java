package com.chitts.service.impl;

import com.chitts.config.DataSourceTestConfig;
import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dto.DtoEmployeeFull;
import com.chitts.dto.DtoEmployeeShort;
import com.chitts.model.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class})
@WebAppConfiguration
@DisplayName("Integration Employee Service Test")
@SqlGroup({
        @Sql(value = "classpath:db/test-data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/clean-up.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
public class EmployeeServiceImplTest {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeServiceImplTest(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    @DisplayName("should delete Employee by id")
    public void delete() throws EmployeeDaoException {
        log.info("TEST method: delete Employee");
        final List<DtoEmployeeShort> employeesList = employeeService.getAll();

        Assertions.assertNotNull(employeesList);
        employeeService.delete(3);
        final List<DtoEmployeeShort> employeesListAfterDeleting = employeeService.getAll();
        Assertions.assertNotNull(employeesListAfterDeleting);
        Assertions.assertEquals(employeesListAfterDeleting.size(), 2);
    }

    @Test
    @DisplayName("should return all Employees")
    public void getAll() throws EmployeeDaoException {
        log.info("TEST method: get all Employees");
        final List<DtoEmployeeShort> employeeList = employeeService.getAll();

        Assertions.assertNotNull(employeeList);
        Assertions.assertEquals(employeeList.size(), 3);
    }

    @Test
    @DisplayName("should return Employee by id")
    public void getById() throws EmployeeDaoException {
        log.info("TEST method: get Employee by id");
        final DtoEmployeeFull employee = employeeService.getById(1);

        Assertions.assertEquals(employee.getFirstName(), "Bob");
        Assertions.assertEquals(employee.getLastName(), "Marley");
        Assertions.assertEquals(employee.getDepartmentId(), 2);
        Assertions.assertEquals(employee.getJobTitle(), "singer");
        Assertions.assertEquals(employee.getGender(), Gender.MALE);
    }

    @Test
    @DisplayName("should save new Employee")
    public void save() throws EmployeeDaoException {
        log.info("TEST method: save Employee");
        final DtoEmployeeFull employee = new DtoEmployeeFull
                (4,
                        "Dead",
                        "Pool",
                        2L,
                        "soldier",
                        Gender.MALE,
                        LocalDate.now().minusDays(1)
                );
        employeeService.save(employee);
        final List<DtoEmployeeShort> employeeList = employeeService.getAll();
        Assertions.assertNotNull(employeeList);
        Assertions.assertEquals(employeeList.size(), 4);
    }

    @Test
    @DisplayName("should update Employee by id")
    public void update() throws EmployeeDaoException {
        log.info("TEST method: update Employee");
        final int id = 2;
        final DtoEmployeeFull employeeForUpdating = employeeService.getById(id);

        employeeForUpdating.setFirstName("firstName");
        employeeForUpdating.setLastName("lastName");
        employeeService.update(employeeForUpdating);
        final DtoEmployeeFull employeeAfterUpdating = employeeService.getById(id);
        Assertions.assertEquals(employeeAfterUpdating.getFirstName(), "firstName");
        Assertions.assertEquals(employeeAfterUpdating.getLastName(), "lastName");
    }

}