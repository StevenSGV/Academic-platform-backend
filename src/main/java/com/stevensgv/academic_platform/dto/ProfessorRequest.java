package com.stevensgv.academic_platform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProfessorRequest(
        @NotBlank @Size(max = 50) String firstname,
        @NotBlank @Size(max = 50) String lastname,
        @Email @NotBlank @Size(max = 150) String email,
        @NotBlank @Size(max = 100) String specialty,
        @NotNull Long userId) {
}
