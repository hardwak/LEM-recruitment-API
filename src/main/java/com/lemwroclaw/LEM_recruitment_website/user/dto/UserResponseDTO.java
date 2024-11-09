package com.lemwroclaw.LEM_recruitment_website.user.dto;

public record UserResponseDTO(
        String firstname,
        String lastname,
        String email,
        String role
) {
}
