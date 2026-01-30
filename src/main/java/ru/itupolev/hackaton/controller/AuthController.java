package ru.itupolev.hackaton.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itupolev.hackaton.controller.dto.RegistrationDto;
import ru.itupolev.hackaton.service.AuthService;
import ru.itupolev.hackaton.utils.ErrorMessageCreator;
import ru.itupolev.hackaton.utils.converters.Converters;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final Validator validator;
    private final AuthService authService;

    public AuthController(@Qualifier("registrationDtoValidator") Validator validator, AuthService authService) {
        this.validator = validator;
        this.authService = authService;


    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationDto registrationDTO, BindingResult bindingResult) {
        validator.validate(registrationDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(ErrorMessageCreator.createErrorMessage(bindingResult));
        }

        var user = Converters.convertToUser(registrationDTO);

        authService.register(user);

        return ResponseEntity.ok().build();
    }
}
