package ru.itupolev.hackaton.utils.validators;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itupolev.hackaton.controller.dto.RegistrationDto;
import ru.itupolev.hackaton.service.UserService;

@Component("registrationDtoValidator")
public class RegistrationDtoValidator implements Validator {
    private final UserService userService;

    public RegistrationDtoValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        var dto = (RegistrationDto) target;

        if (!dto.searchingCommand()) {
            if (Strings.isEmpty(dto.teamName().trim())) {
                errors.rejectValue("teamName", "", "Нужно указать название команды");
            }
        }

        if (!errors.hasFieldErrors("phoneNumber")) {
            var user = userService.getUserWithPhone(dto.phoneNumber());

            if (user.isPresent()) {
                errors.rejectValue("phoneNumber", "", "С этим телефоном уже регистрировались");
            }
        }

        if (!errors.hasFieldErrors("email")) {
            var user = userService.getUserWithEmail(dto.email());

            if (user.isPresent()) {
                errors.rejectValue("email", "", "С этим email уже регистрировались");
            }
        }

        if (!errors.hasFieldErrors("telegramName")) {
            var user = userService.getUserWithTelegram(dto.telegramName());

            if (user.isPresent()) {
                errors.rejectValue("telegramName", "", "Пользователь с таким телеграм уже есть");
            }
        }
    }
}
