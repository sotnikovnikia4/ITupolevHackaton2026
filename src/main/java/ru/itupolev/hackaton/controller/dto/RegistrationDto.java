package ru.itupolev.hackaton.controller.dto;

import jakarta.validation.constraints.*;

public record RegistrationDto(
        @NotEmpty(message = "Имя не должно быть пустым")
        String name,

        @NotEmpty(message = "Фамилия не должна быть пустой")
        String surname,

        String patronymic,

        @Email(message = "Email должен быть в формате 'email@example.com'")
        @NotEmpty(message = "Email не должен быть пустым")
        String email,

        @NotNull(message = "Нужно указать тимлид ты или нет")
        Boolean teamLead,

        @NotNull(message = "Нужно указать, ищешь ты команду или нет")
        Boolean searchingCommand,

        @NotEmpty(message = "Нужно указать свою организацию")
        String organization,

        String teamName,

        @NotBlank(message = "Ник в Telegram не может быть пустым")
        @Pattern(regexp = "^@[a-zA-Z]\\w{4,31}$", message = "Ник в Telegram должен начинаться с символа '@' и должен быть написан правильно")
        String telegramName,

        @Pattern(regexp = "^(8|\\+7)(\\s|\\(|-)?(\\d{3})(\\s|\\)|-)?(\\d{3})(\\s|-)?(\\d{2})(\\s|-)?(\\d{2})$",
                message = "Телефон должен быть в формате +79998887766 или 89998887766")
        @NotEmpty(message = "Нужно указать свой телефон")
        String phoneNumber
) {
}
