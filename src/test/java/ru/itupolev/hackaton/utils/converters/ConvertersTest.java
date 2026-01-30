package ru.itupolev.hackaton.utils.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.itupolev.hackaton.controller.dto.RegistrationDto;
import ru.itupolev.hackaton.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
class ConvertersTest {

    @Test
    @DisplayName("Должен корректно конвертировать все поля, включая данные команды, когда searchingCommand = false")
    void shouldConvertAllFields_WhenNotSearchingCommand() {
        // Arrange
        RegistrationDto registrationDto = new RegistrationDto(
                "Иван",                  // name
                "Иванов",                // surname
                "Иванович",              // patronymic
                "ivan@example.com",      // email
                true,                    // teamLead (является капитаном)
                false,                   // searchingCommand (НЕ ищет команду, значит есть своя)
                "МГТУ",                  // organization
                "SuperTeam",             // teamName
                "@ivanov",               // telegramName
                "+79990000000"           // phoneNumber
        );

        // Act
        User user = Converters.convertToUser(registrationDto);

        // Assert
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(registrationDto.name());
        assertThat(user.getSurname()).isEqualTo(registrationDto.surname());
        assertThat(user.getPatronymic()).isEqualTo(registrationDto.patronymic());
        assertThat(user.getEmail()).isEqualTo(registrationDto.email());
        assertThat(user.getOrganization()).isEqualTo(registrationDto.organization());
        assertThat(user.getPhoneNumber()).isEqualTo(registrationDto.phoneNumber());
        assertThat(user.getTelegramName()).isEqualTo(registrationDto.telegramName());

        // Проверяем логику флага searchingCommand
        assertThat(user.isSearchingCommand()).isFalse();

        // Проверяем, что поля команды ПРИСВОИЛИСЬ
        assertThat(user.getTeamName()).isEqualTo("SuperTeam");
        assertThat(user.isTeamLead()).isTrue();
    }

    @Test
    @DisplayName("Должен игнорировать поля команды, когда searchingCommand = true")
    void shouldIgnoreTeamFields_WhenSearchingCommand() {
        // Arrange
        RegistrationDto registrationDto = new RegistrationDto(
                "Петр",
                "Петров",
                null,
                "petr@example.com",
                true,                    // teamLead (передаем true, но конвертер должен это проигнорировать)
                true,                    // searchingCommand (ИЩЕТ команду)
                "МГУ",
                "FakeTeamName",          // teamName (тоже должно быть проигнорировано)
                "@petrov",
                "+78880000000"
        );

        // Act
        User user = Converters.convertToUser(registrationDto);

        // Assert
        assertThat(user.getName()).isEqualTo("Петр");
        assertThat(user.isSearchingCommand()).isTrue();

        // Проверяем специфичную логику:
        // Если searchingCommand = true, то блок if в конвертере пропускается.
        // Следовательно, teamName остается null, а teamLead остается false (дефолтное значение boolean)
        assertThat(user.getTeamName()).isNull();
        assertThat(user.isTeamLead()).isFalse();
    }

    // ... (предыдущие тесты convertToUser)

    @Test
    @DisplayName("Должен нормализовать номер телефона, начинающийся с 8")
    void shouldNormalizePhoneNumber_WhenStartsWith8() {
        // Arrange
        String input = "8(999)000-00-00";

        // Act
        String result = Converters.normalizePhoneNumber(input);

        // Assert
        // Ожидаем, что 8 заменится на +7, а скобки и дефисы уйдут
        assertThat(result).isEqualTo("+79990000000");
    }

    @Test
    @DisplayName("Должен нормализовать номер телефона, начинающийся с +7")
    void shouldNormalizePhoneNumber_WhenStartsWithPlus7() {
        // Arrange
        String input = "+7 (999) 000-00-00";

        // Act
        String result = Converters.normalizePhoneNumber(input);

        // Assert
        // Ожидаем, что +7 останется, а пробелы, скобки и дефисы уйдут
        assertThat(result).isEqualTo("+79990000000");
    }

    @Test
    @DisplayName("Должен оставлять номер без изменений, если он уже нормализован")
    void shouldReturnSamePhoneNumber_WhenAlreadyNormalized() {
        // Arrange
        String input = "+79990000000";

        // Act
        String result = Converters.normalizePhoneNumber(input);

        // Assert
        assertThat(result).isEqualTo("+79990000000");
    }

    @Test
    @DisplayName("Должен удалять любые нецифровые символы кроме плюса")
    void shouldRemoveNonDigitCharactersFromPhoneNumber() {
        // Arrange
        // Добавляем лишние пробелы и тире в нестандартных местах
        String input = "+7- 999 - 00 0 -- 00 00";

        // Act
        String result = Converters.normalizePhoneNumber(input);

        // Assert
        assertThat(result).isEqualTo("+79990000000");
    }

    @Test
    @DisplayName("Должен приводить email к нижнему регистру")
    void shouldNormalizeEmail() {
        // Arrange
        String input = "Ivan.Ivanov@Example.COM";

        // Act
        String result = Converters.normalizeEmail(input);

        // Assert
        assertThat(result).isEqualTo("ivan.ivanov@example.com");
    }

    @Test
    @DisplayName("Должен приводить telegram ник к нижнему регистру")
    void shouldNormalizeTelegramName() {
        // Arrange
        String input = "@SuperCoder2000";

        // Act
        String result = Converters.normalizeTelegramName(input);

        // Assert
        assertThat(result).isEqualTo("@supercoder2000");
    }
}