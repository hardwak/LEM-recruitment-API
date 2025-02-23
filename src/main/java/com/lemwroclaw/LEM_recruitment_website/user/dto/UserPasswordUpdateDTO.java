package com.lemwroclaw.LEM_recruitment_website.user.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record UserPasswordUpdateDTO(
        @NotBlank
        String oldPassword,
        @NotBlank
        String newPassword
) {

    @AssertTrue(message = "The new password must be different from the old password.")
    public boolean hasPasswordChanged() {
        return !oldPassword.equals(newPassword);
    }

}
