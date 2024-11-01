package com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto;

import java.util.Date;

public record RecruitmentResponseDTO(
        String title,
        String description,
        String department,
        Date startDate,
        Date endDate
) {
}
