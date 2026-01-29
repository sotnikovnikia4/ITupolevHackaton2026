package ru.itupolev.hackaton.service.impl;

import org.springframework.stereotype.Service;
import ru.itupolev.hackaton.entity.User;
import ru.itupolev.hackaton.repository.UserRepository;
import ru.itupolev.hackaton.service.UserService;
import ru.itupolev.hackaton.utils.converters.Convertors;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserWithPhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(Convertors.normalizePhoneNumber(phoneNumber));
    }

    @Override
    public Optional<User> getUserWithEmail(String email) {
        return userRepository.findByEmail(Convertors.normalizeEmail(email));
    }
}
