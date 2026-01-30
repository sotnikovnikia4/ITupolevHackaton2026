package ru.itupolev.hackaton.service;

import ru.itupolev.hackaton.controller.dto.EmailBroadcastDto;

import java.util.Set;
import java.util.regex.Pattern;

public interface BroadcastService {
    // Паттерн для поиска конструкций вида ${word}
    Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{([^}]+)\\}");

    // Разрешенные поля
    Set<String> ALLOWED_FIELDS = Set.of(
            "name", "surname", "email", "organization",
            "team", "telegram", "phone", "patronymic"
    );

    String DEFAULT_SUBJECT = "Notification";

    void sendBroadcast(EmailBroadcastDto request);
}