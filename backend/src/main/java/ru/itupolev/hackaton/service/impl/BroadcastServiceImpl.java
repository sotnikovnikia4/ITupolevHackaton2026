package ru.itupolev.hackaton.service.impl;

import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itupolev.hackaton.controller.dto.EmailBroadcastDto;
import ru.itupolev.hackaton.entity.User;
import ru.itupolev.hackaton.exception.TemplateException;
import ru.itupolev.hackaton.repository.UserRepository;
import ru.itupolev.hackaton.service.BroadcastService;
import ru.itupolev.hackaton.service.EmailSenderService;

import java.util.*;
import java.util.regex.Matcher;

@Service
public class BroadcastServiceImpl implements BroadcastService {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastServiceImpl.class);

    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    public BroadcastServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

    /**
     * Основной метод рассылки
     */
    @Transactional(readOnly = true)
    public void sendBroadcast(EmailBroadcastDto request) {
        logger.info("Sending Email Broadcast Request");

        var template = request.template();

        var subject = request.subject() != null ? request.subject() : DEFAULT_SUBJECT;

        validateTemplate(template);
        validateTemplate(subject);

        var users = userRepository.findAll();
        logger.info("Found {} Users", users.size());
        send(users, template, subject);
    }

    private void send(List<User> users, String template, String subject) {
        for (User user : users) {
            if (user.isSearchingCommand()) {
                continue;
            }


            String personalizedMessage = replacePlaceholders(template, user);
            String personalizedSubject = replacePlaceholders(subject, user);
            emailSenderService.sendEmail(user.getEmail(), personalizedSubject, personalizedMessage);
        }
    }

    /**
     * Проверяет, что в шаблоне нет недопустимых полей.
     * Если есть - выбрасывает исключение.
     */
    private void validateTemplate(String template) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        Set<String> foundPlaceholders = new HashSet<>();

        while (matcher.find()) {
            foundPlaceholders.add(matcher.group(1)); // group(1) берет текст внутри скобок
        }

        // Ищем поля, которых нет в списке разрешенных
        List<String> unknownFields = foundPlaceholders.stream()
                .filter(field -> !ALLOWED_FIELDS.contains(field))
                .toList();

        if (!unknownFields.isEmpty()) {
            throw new TemplateException("Template contains unsupported fields: " + unknownFields);
        }
    }

    /**
     * Заменяет плейсхолдеры на данные конкретного пользователя
     */
    private String replacePlaceholders(String template, User user) {
        Map<String, String> values = new HashMap<>();
        values.put("name", user.getName());
        values.put("surname", user.getSurname());
        values.put("email", user.getEmail());
        values.put("organization", user.getOrganization());
        // teamName может быть null, заменяем на пустую строку или дефолт
        values.put("team", user.getTeamName() != null ? user.getTeamName() : "");
        values.put("telegram", user.getTelegramName());
        values.put("phone", user.getPhoneNumber());
        values.put("patronymic", user.getPatronymic() != null ? user.getPatronymic() : "");

        String result = template;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            result = result.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return result;
    }
}