package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplicationCreationDTO(
        @NotNull Long userId,
        @NotNull Long recruitmentId,
        @NotBlank String content
) {
}
