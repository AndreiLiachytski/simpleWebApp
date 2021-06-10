package com.chitts.service.dto.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(
        value = "Employee Model",
        subTypes = {EmployeeShortDto.class},
        description = "Sample model for the documentation")
public class EmployeeShortDto {

    @ApiModelProperty(
            value = "Unique number id",
            example = "1",
            position = 1)
    private long employeeId;

    @NotEmpty(message = "The firstName name must not be empty")
    @ApiModelProperty(
            value = "Employee firstname",
            example = "Bob",
            required = true,
            position = 2)
    private String firstName;

    @NotEmpty(message = "The lastName name must not be empty")
    @ApiModelProperty(
            value = "Employee lastname",
            example = "Marley",
            required = true,
            position = 3)
    private String lastName;

    @Range(min = 1, max = 10, message = "The departmentId value must be between 1 and 10 inclusive")
    @ApiModelProperty(
            value = "Employee's department ID",
            example = "1",
            required = true,
            position = 4)
    private long departmentId;

}