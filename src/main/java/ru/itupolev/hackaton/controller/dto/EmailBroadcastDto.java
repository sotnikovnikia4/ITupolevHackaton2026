package ru.itupolev.hackaton.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailBroadcastDto(
        @NotBlank(message = "Template must not be empty")
        String template,

        String subject
) {
}