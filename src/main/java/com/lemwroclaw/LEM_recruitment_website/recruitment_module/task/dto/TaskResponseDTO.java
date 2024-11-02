package com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto;

import java.util.List;

public record TaskResponseDTO(
        String title,
        String description,
        List<String> recruitments
) {
}
