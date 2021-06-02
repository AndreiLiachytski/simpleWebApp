package com.chitts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoEmployeeShort {

    private long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;

}