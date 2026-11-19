package com.example.springbootdemo.feature.employee;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO (
    String id,
    @NotBlank String name,
    String email,
    Long departmentId
) { }
