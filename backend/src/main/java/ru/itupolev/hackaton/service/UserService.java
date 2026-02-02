package ru.itupolev.hackaton.service;


import org.springframework.data.domain.*;
import ru.itupolev.hackaton.entity.*;

import java.util.*;

public interface UserService {
    Optional<User> getUserWithPhone(String phoneNumber);

    Optional<User> getUserWithEmail(String email);

    Optional<User> getUserWithTelegram(String telegramName);

    Page<User> getUsers(int pageNumber, int pageSize);
}
