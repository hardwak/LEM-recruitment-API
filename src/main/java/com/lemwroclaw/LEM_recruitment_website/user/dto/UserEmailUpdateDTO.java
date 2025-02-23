package com.lemwroclaw.LEM_recruitment_website.user.dto;

import jakarta.validation.constraints.Email;

public record UserEmailUpdateDTO(
        @Email String email
) {
}
