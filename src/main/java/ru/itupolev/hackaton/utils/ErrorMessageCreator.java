package ru.itupolev.hackaton.utils;

import org.springframework.validation.BindingResult;

public class ErrorMessageCreator {
    private ErrorMessageCreator() {
    }

    public static String createErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        for (var error : bindingResult.getFieldErrors()) {
            errorMessage.append(error.getField());
            errorMessage.append(": ");
            errorMessage.append(error.getDefaultMessage());
            errorMessage.append(";");
        }
        errorMessage.setLength(errorMessage.length() - 1);
        return errorMessage.toString();
    }
}
