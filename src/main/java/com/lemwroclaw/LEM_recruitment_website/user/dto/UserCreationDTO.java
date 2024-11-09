package com.lemwroclaw.LEM_recruitment_website.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreationDTO(
        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String role
) {
}
