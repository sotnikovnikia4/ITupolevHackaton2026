package ru.itupolev.hackaton.service.impl;

import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.itupolev.hackaton.entity.*;
import ru.itupolev.hackaton.repository.*;
import ru.itupolev.hackaton.service.*;
import ru.itupolev.hackaton.utils.converters.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserWithPhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(Converters.normalizePhoneNumber(phoneNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserWithEmail(String email) {
        return userRepository.findByEmail(Converters.normalizeEmail(email));
    }

    @Override
    public Optional<User> getUserWithTelegram(String telegramName) {
        return userRepository.findByTelegramName(Converters.normalizeTelegramName(telegramName));
    }

    @Override
    public Page<User> getUsers(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber - 1, pageSize, Sort.by("teamName")));
    }
}
