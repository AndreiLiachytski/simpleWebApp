package com.chitts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DtoEmployeeShort {
    private long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;

}