package ru.itupolev.hackaton.utils.validators;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.validation.*;
import ru.itupolev.hackaton.controller.dto.*;
import ru.itupolev.hackaton.entity.*;
import ru.itupolev.hackaton.service.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationDtoValidatorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private RegistrationDtoValidator validator;

    private RegistrationDto validDto;

    @BeforeEach
    void setUp() {
        // Создаем базовый валидный объект (используем Record или Class в зависимости от вашей реализации)
        validDto = new RegistrationDto(
                "Иван", "Иванов", "Иванович", "ivan@example.com",
                false, true, "МГТУ", "RocketTeam",
                "@ivanov", "+79990000000"
        );
    }

    @Test
    @DisplayName("1. Если Errors уже содержит ошибки, валидация должна прерваться")
    void shouldStopValidationIfErrorsAlreadyPresent() {
        Errors errors = new BeanPropertyBindingResult(validDto, "registrationDto");
        errors.reject("some.code", "Existing error");

        validator.validate(validDto, errors);

        // Проверяем, что методы сервиса НЕ вызывались, так как сработал early return
        verifyNoInteractions(userService);
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    @DisplayName("2. Если searchingCommand = false и teamName пустой — ожидаем ошибку")
    void shouldRejectWhenNotSearchingCommandAndTeamNameIsEmpty() {
        RegistrationDto dtoWithoutTeam = new RegistrationDto(
                "Иван", "Иванов", "Иванович", "ivan@example.com",
                false, false, "МГТУ", "", // searchingCommand = false, teamName = ""
                "@ivanov", "+79990000000"
        );
        Errors errors = new BeanPropertyBindingResult(dtoWithoutTeam, "registrationDto");

        validator.validate(dtoWithoutTeam, errors);

        assertTrue(errors.hasFieldErrors("teamName"));
        assertEquals("Нужно указать название команды", errors.getFieldError("teamName").getDefaultMessage());
    }

    @Test
    @DisplayName("3a. Если номер телефона уже занят — ожидаем ошибку")
    void shouldRejectIfPhoneExists() {
        Errors errors = new BeanPropertyBindingResult(validDto, "registrationDto");
        when(userService.getUserWithPhone(validDto.phoneNumber())).thenReturn(Optional.of(new User())); // Имитируем наличие юзера

        validator.validate(validDto, errors);

        assertTrue(errors.hasFieldErrors("phoneNumber"));
        assertEquals("С этим телефоном уже регистрировались", errors.getFieldError("phoneNumber").getDefaultMessage());
    }

    @Test
    @DisplayName("3b. Если email уже занят — ожидаем ошибку")
    void shouldRejectIfEmailExists() {
        Errors errors = new BeanPropertyBindingResult(validDto, "registrationDto");
        when(userService.getUserWithEmail(validDto.email())).thenReturn(Optional.of(new User()));

        validator.validate(validDto, errors);

        assertTrue(errors.hasFieldErrors("email"));
        assertEquals("С этим email уже регистрировались", errors.getFieldError("email").getDefaultMessage());
    }

    @Test
    @DisplayName("3c. Если telegram ник уже занят — ожидаем ошибку")
    void shouldRejectIfTelegramExists() {
        Errors errors = new BeanPropertyBindingResult(validDto, "registrationDto");
        when(userService.getUserWithTelegram(validDto.telegramName())).thenReturn(Optional.of(new User()));

        validator.validate(validDto, errors);

        assertTrue(errors.hasFieldErrors("telegramName"));
        assertEquals("Пользователь с таким телеграм уже есть", errors.getFieldError("telegramName").getDefaultMessage());
    }
}