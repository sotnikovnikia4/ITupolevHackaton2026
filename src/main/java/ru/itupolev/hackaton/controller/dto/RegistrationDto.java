package ru.itupolev.hackaton.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
        String telegramName,

        @Pattern(regexp = "^(8|\\+7)(\\s|\\(|-)?(\\d{3})(\\s|\\)|-)?(\\d{3})(\\s|-)?(\\d{2})(\\s|-)?(\\d{2})$")
        @NotEmpty
        String phoneNumber
) {
}
