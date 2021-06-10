package com.chitts.service.impl;

import com.chitts.dao.entity.Gender;
import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;
import com.chitts.service.EmployeeService;
import com.chitts.service.dto.employee.EmployeeFullDto;
import com.chitts.service.dto.employee.EmployeeShortDto;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EmployeeServiceImpl_Unit_Test {

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        verifyNoInteractions(employeeService);
    }

    private EmployeeFullDto testingEmployee = EmployeeFullDto.builder()
            .firstName("firstName")
            .lastName("lastName")
            .departmentId(1)
            .jobTitle("jobTitle")
            .gender(Gender.MALE)
            .dateOfBirth(LocalDate.now())
            .build();

    @Test
    void getAll() throws EmployeeDaoException {
        // given
        final List<EmployeeShortDto> employeeList = new LinkedList<>();
        employeeList.add(testingEmployee);
        employeeList.add(testingEmployee);
        when(employeeService.getAll()).thenReturn(employeeList);

        // when
        final List<EmployeeShortDto> resultListEmployee = employeeService.getAll();

        // then
        Mockito.verify(employeeService, Mockito.times(1)).getAll();
        assertNotNull(resultListEmployee);
    }

    @Test
    void getById() throws EmployeeDaoException, EmployeeNotFoundException {
        // given
        when(employeeService.getById(any(Long.class))).thenReturn(testingEmployee);

        // when
        final EmployeeFullDto employeeFullDto = employeeService.getById(any(Long.class));

        // then
        Mockito.verify(employeeService, Mockito.times(1)).getById(0);
        assertNotNull(employeeFullDto);
        assertThat(employeeFullDto, CoreMatchers.notNullValue());
        assertThat(testingEmployee.getFirstName(), CoreMatchers.is(employeeFullDto.getFirstName()));
        assertThat(testingEmployee.getLastName(), CoreMatchers.is(employeeFullDto.getLastName()));
        assertThat(testingEmployee.getJobTitle(), CoreMatchers.is(employeeFullDto.getJobTitle()));
        assertThat(testingEmployee.getGender(), CoreMatchers.is(employeeFullDto.getGender()));
        assertThat(testingEmployee.getDepartmentId(), CoreMatchers.is(employeeFullDto.getDepartmentId()));
        assertThat(testingEmployee.getDateOfBirth(), CoreMatchers.is(employeeFullDto.getDateOfBirth()));
    }

    @Test
    void save() throws EmployeeDaoException {
        // given
        when(employeeService.save(testingEmployee)).thenReturn(any(Long.class));

        // when
        final Long employeeId = employeeService.save(testingEmployee);

        // then
        Mockito.verify(employeeService, Mockito.times(1)).save(testingEmployee);
        assertThat(employeeId, CoreMatchers.notNullValue());
    }

    @Test
    void update() throws EmployeeDaoException, EmployeeNotFoundException {
        // given
        final EmployeeFullDto employeeForUpdate = EmployeeFullDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .departmentId(1)
                .jobTitle("jobTitle")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.now())
                .build();
        doAnswer((invocation -> {
            testingEmployee = invocation.getArgument(0);
            return null;
        })).when(employeeService).update(employeeForUpdate);

        // when
        employeeService.update(employeeForUpdate);

        // then
        Mockito.verify(employeeService, Mockito.times(1)).update(employeeForUpdate);
        Assertions.assertEquals(testingEmployee.getFirstName(), employeeForUpdate.getFirstName());
        Assertions.assertEquals(testingEmployee.getLastName(), employeeForUpdate.getLastName());
        Assertions.assertEquals(testingEmployee.getDepartmentId(), employeeForUpdate.getDepartmentId());
        Assertions.assertEquals(testingEmployee.getJobTitle(), employeeForUpdate.getJobTitle());
        Assertions.assertEquals(testingEmployee.getGender(), employeeForUpdate.getGender());
        Assertions.assertEquals(testingEmployee.getDateOfBirth(), employeeForUpdate.getDateOfBirth());
    }

    @Test
    void delete() throws EmployeeDaoException, EmployeeNotFoundException {
        // given
        when(employeeService.delete(any(Long.class))).thenReturn(1L);

        // when
        final long resultOfDelete = employeeService.delete(1);

        // then
        Mockito.verify(employeeService, Mockito.times(1)).delete(any(Long.class));
        assertThat(resultOfDelete, CoreMatchers.equalTo(1L));
    }

    @Test
    public void getAllTestingException() throws EmployeeDaoException {
        // given
        Mockito.doThrow(EmployeeDaoException.class).when(employeeService).getAll();

        // when
        final Throwable throwable = assertThrows(EmployeeDaoException.class, () -> employeeService.getAll());

        // then
        verify(employeeService).getAll();
        assertThat(throwable, CoreMatchers.instanceOf(EmployeeDaoException.class));
    }

    @Test
    public void getByIdTestingException() throws EmployeeDaoException, EmployeeNotFoundException {
        // given
        Mockito.doThrow(EmployeeNotFoundException.class).when(employeeService).getById(any(Long.class));

        // when
        final Throwable throwable = assertThrows(EmployeeNotFoundException.class, () -> employeeService.getById(any(Long.class)));

        // then
        verify(employeeService).getById(any(Long.class));
        assertThat(throwable, CoreMatchers.instanceOf(EmployeeNotFoundException.class));
    }

    @Test
    public void saveTestingException() throws EmployeeDaoException {
        // given
        Mockito.doThrow(EmployeeDaoException.class).when(employeeService).save(testingEmployee);

        // when
        final Throwable throwable = assertThrows(EmployeeDaoException.class, () -> employeeService.save(testingEmployee));

        // then
        verify(employeeService).save(testingEmployee);
        assertThat(throwable, CoreMatchers.instanceOf(EmployeeDaoException.class));
    }

    @Test
    public void updateTestingException() throws EmployeeDaoException, EmployeeNotFoundException {
        // given
        Mockito.doThrow(EmployeeNotFoundException.class).when(employeeService).update(testingEmployee);

        // when
        final Throwable throwable = assertThrows(EmployeeNotFoundException.class, () -> employeeService.update(testingEmployee));

        // then
        verify(employeeService).update(testingEmployee);
        assertThat(throwable, CoreMatchers.instanceOf(EmployeeNotFoundException.class));
    }

    @Test
    public void deleteTestingException() throws EmployeeDaoException, EmployeeNotFoundException {
        // given
        Mockito.doThrow(EmployeeDaoException.class).when(employeeService).delete(1);

        // when
        final Throwable throwable = assertThrows(EmployeeDaoException.class, () -> employeeService.delete(1));

        // then
        verify(employeeService).delete(1);
        assertThat(throwable, CoreMatchers.instanceOf(EmployeeDaoException.class));
    }

}