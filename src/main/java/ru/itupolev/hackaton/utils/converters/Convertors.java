package ru.itupolev.hackaton.utils.converters;

import ru.itupolev.hackaton.controller.dto.RegistrationDto;
import ru.itupolev.hackaton.entity.User;

public class Convertors {
    public static User convertToUser(RegistrationDto registrationDto) {
        var user = new User();
        user.setEmail(registrationDto.email());
        user.setName(registrationDto.name());
        user.setSurname(registrationDto.surname());
        user.setOrganization(registrationDto.organization());
        user.setPhoneNumber(registrationDto.phoneNumber());
        user.setTelegramName(registrationDto.telegramName());
        user.setSearchingCommand(registrationDto.searchingCommand());

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
        return email.toLowerCase();
    }
}
