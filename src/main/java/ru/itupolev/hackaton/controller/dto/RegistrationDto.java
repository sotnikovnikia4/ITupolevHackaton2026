package ru.itupolev.hackaton.controller.dto;

import jakarta.validation.constraints.*;

public record RegistrationDto(
        @NotEmpty
        String name,

        @NotEmpty
        String surname,

        String patronymic,

        @Email
        @NotEmpty
        String email,

        @NotNull
        Boolean teamLead,

        @NotNull
        Boolean searchingCommand,

        @NotEmpty
        String organization,

        String teamName,

        @NotEmpty
        @NotBlank(message = "Ник в Telegram не может быть пустым")
        @Pattern(regexp = "^@.*", message = "Ник в Telegram должен начинаться с символа '@'")
        String telegramName,

        @Pattern(regexp = "^(8|\\+7)(\\s|\\(|-)?(\\d{3})(\\s|\\)|-)?(\\d{3})(\\s|-)?(\\d{2})(\\s|-)?(\\d{2})$")
        @NotEmpty
        String phoneNumber
) {
}
