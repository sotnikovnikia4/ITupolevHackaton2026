package ru.itupolev.hackaton.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.itupolev.hackaton.controller.dto.*;
import ru.itupolev.hackaton.service.*;
import ru.itupolev.hackaton.utils.converters.*;
import ru.itupolev.hackaton.utils.validators.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PageDto<UserAnswerDto>> getUsers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        PageRequestValidator.validate(pageNumber, pageSize);
        return ResponseEntity.ok(Converters.convertToUserPageDto(userService.getUsers(pageNumber, pageSize)));
    }
}
