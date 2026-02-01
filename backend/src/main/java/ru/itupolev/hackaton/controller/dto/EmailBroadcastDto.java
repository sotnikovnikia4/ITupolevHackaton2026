package ru.itupolev.hackaton.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailBroadcastDto(
        @NotBlank(message = "Шаблон не должен быть пустым")
        String template,

        String subject
) {
}