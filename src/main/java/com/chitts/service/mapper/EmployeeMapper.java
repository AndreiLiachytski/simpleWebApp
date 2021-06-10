package com.chitts.service.mapper;

import com.chitts.dao.entity.Employee;
import com.chitts.service.dto.employee.EmployeeFullDto;
import com.chitts.service.dto.employee.EmployeeShortDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeFullDto map(Employee entity);

    Employee map(EmployeeFullDto entity);

    @IterableMapping(qualifiedByName = "mapToShort")
    List<EmployeeShortDto> map(List<Employee> employees);

    @Named(value = "mapToShort")
    EmployeeShortDto mapToShort(Employee employee);

}