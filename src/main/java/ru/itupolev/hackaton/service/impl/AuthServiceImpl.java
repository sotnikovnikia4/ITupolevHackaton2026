package ru.itupolev.hackaton.service.impl;

import org.springframework.stereotype.Service;
import ru.itupolev.hackaton.entity.User;
import ru.itupolev.hackaton.repository.UserRepository;
import ru.itupolev.hackaton.service.AuthService;
import ru.itupolev.hackaton.utils.converters.Convertors;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        user.setPhoneNumber(Convertors.normalizePhoneNumber(user.getPhoneNumber()));
        user.setEmail(Convertors.normalizeEmail(user.getEmail()));
        userRepository.save(user);
    }
}
