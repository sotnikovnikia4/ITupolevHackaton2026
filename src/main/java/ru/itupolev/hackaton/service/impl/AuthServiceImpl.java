package ru.itupolev.hackaton.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itupolev.hackaton.entity.User;
import ru.itupolev.hackaton.repository.UserRepository;
import ru.itupolev.hackaton.service.AuthService;
import ru.itupolev.hackaton.utils.converters.Converters;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void register(User user) {
        user.setPhoneNumber(Converters.normalizePhoneNumber(user.getPhoneNumber()));
        user.setEmail(Converters.normalizeEmail(user.getEmail()));
        userRepository.save(user);
    }
}
