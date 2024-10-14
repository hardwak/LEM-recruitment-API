package com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartmentDTO(
        @NotBlank
        String name
) {}
