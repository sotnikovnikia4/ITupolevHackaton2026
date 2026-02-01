package ru.itupolev.hackaton.service;


import ru.itupolev.hackaton.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserWithPhone(String phoneNumber);

    Optional<User> getUserWithEmail(String email);

    Optional<User> getUserWithTelegram(String telegramName);
}
