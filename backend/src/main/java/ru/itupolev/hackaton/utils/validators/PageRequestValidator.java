package ru.itupolev.hackaton.utils.validators;

import jakarta.validation.*;

public class PageRequestValidator {
    public static void validate(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            throw new ValidationException("Page number should be greater than zero");
        }
        if (pageSize <= 0) {
            throw new ValidationException("Page size should be greater than 0");
        }
    }
}
