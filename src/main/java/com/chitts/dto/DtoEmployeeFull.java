package com.chitts.dto;

import com.chitts.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoEmployeeFull {

    private long employeeId;
    @NotEmpty(message = "The firstName name must not be empty")
    private String firstName;
    @NotEmpty(message = "The lastName name must not be empty")
    private String lastName;
    @Range(min = 1, max = 10, message = "The departmentId value must be between 1 and 10 inclusive")
    private long departmentId;
    @NotEmpty(message = "The jobTitle must not be empty")
    private String jobTitle;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

}