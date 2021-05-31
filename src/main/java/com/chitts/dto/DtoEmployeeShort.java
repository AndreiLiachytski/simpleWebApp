package com.chitts.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoEmployeeShort {

    private long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;

}