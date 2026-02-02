package ru.itupolev.hackaton.controller.dto;

public record UserAnswerDto(
        String name,

        String surname,

        String patronymic,

        String email,

        Boolean teamLead,

        Boolean searchingCommand,

        String organization,

        String teamName,

        String telegramName,

        String phoneNumber
) {
}
