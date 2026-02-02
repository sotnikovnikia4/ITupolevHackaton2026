package ru.itupolev.hackaton.utils.converters;

import org.springframework.data.domain.*;
import ru.itupolev.hackaton.controller.dto.*;
import ru.itupolev.hackaton.entity.*;

import java.util.*;

public class Converters {
    private Converters() {
    }

    public static User convertToUser(RegistrationDto registrationDto) {
        var user = new User();
        user.setEmail(registrationDto.email());
        user.setName(registrationDto.name());
        user.setSurname(registrationDto.surname());
        user.setOrganization(registrationDto.organization());
        user.setPhoneNumber(registrationDto.phoneNumber());
        user.setTelegramName(registrationDto.telegramName());
        user.setSearchingCommand(registrationDto.searchingCommand());
        user.setPatronymic(registrationDto.patronymic());

        if (!user.isSearchingCommand()) {
            user.setTeamName(registrationDto.teamName());
            user.setTeamLead(registrationDto.teamLead());
        }

        return user;
    }

    public static String normalizePhoneNumber(String phoneNumber) {
        StringBuilder normalizedPhoneNumber = new StringBuilder();

        for (var c : phoneNumber.toCharArray()) {
            if (Character.isDigit(c) || c == '+') {
                normalizedPhoneNumber.append(c);
            }
        }

        if (normalizedPhoneNumber.charAt(0) == '8') {
            normalizedPhoneNumber.deleteCharAt(0);
            return "+7" + normalizedPhoneNumber;
        }

        return normalizedPhoneNumber.toString();
    }

    public static String normalizeEmail(String email) {
        return email.toLowerCase().trim();
    }

    public static String normalizeTelegramName(String telegramName) {
        return telegramName.toLowerCase().trim();
    }

    public static PageDto<UserAnswerDto> convertToUserPageDto(Page<User> users) {
        var result = new LinkedList<UserAnswerDto>();

        users.getContent().forEach(u -> {
            result.add(convertToUserAnswerDto(u));
        });

        return new PageDto<>(
                users.getNumber(),
                users.getSize(),
                users.getTotalElements(),
                users.getTotalPages(),
                result
        );
    }

    public static UserAnswerDto convertToUserAnswerDto(User user) {
        return new UserAnswerDto(
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getEmail(),
                user.isTeamLead(),
                user.isSearchingCommand(),
                user.getOrganization(),
                user.getTeamName(),
                user.getTelegramName(),
                user.getPhoneNumber()
        );
    }
}
