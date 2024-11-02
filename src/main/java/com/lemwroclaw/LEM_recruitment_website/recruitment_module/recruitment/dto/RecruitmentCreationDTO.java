package com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record RecruitmentCreationDTO(
        @NotBlank String title,
        String description,
        @NotBlank
        String department,
        Date startDate,
        @FutureOrPresent
        Date endDate
) {
}
