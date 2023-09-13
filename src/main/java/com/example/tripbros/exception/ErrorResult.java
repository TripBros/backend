package com.example.tripbros.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;
}
