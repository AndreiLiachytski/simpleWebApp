package com.chitts.service.dto.employee;

import com.chitts.dao.entity.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(
        value = "Employee Model",
        subTypes = {EmployeeFullDto.class},
        description = "Sample model for the documentation")
public class EmployeeFullDto extends EmployeeShortDto {

    @NotEmpty(message = "The jobTitle must not be empty")
    @ApiModelProperty(
            value = "Job title",
            example = "singer",
            required = true,
            position = 5)
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(
            value = "Gender",
            allowableValues = "MALE, FEMALE",
            required = true,
            position = 6)
    private Gender gender;

    @Past(message = "Only past time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty(
            value = "Date of birth",
            example = "1945-02-06",
            required = true,
            position = 7)
    private LocalDate dateOfBirth;

}