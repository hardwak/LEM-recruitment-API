package com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TaskCreationDTO(
        @NotBlank String title,
        @NotBlank String description,
        List<Long> recruitments
) {
}
