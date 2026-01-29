package ru.itupolev.hackaton.exception;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionMessage> handleException(ValidationException e) {
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
