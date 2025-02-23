package com.lemwroclaw.LEM_recruitment_website.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserPersonalDataUpdateDTO(
        @NotBlank
        String firstname,
        @NotBlank
        String lastname
) {

}

