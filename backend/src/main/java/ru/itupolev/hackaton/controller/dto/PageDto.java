package ru.itupolev.hackaton.controller.dto;

import java.util.*;

public record PageDto<T>(
        int pageNumber,
        int pageSize,

        long totalPages,
        long totalElements,

        List<T> data
) {
}
