package com.skillforge.exceptions;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final boolean success;
    private final String message;
    private final String errorCode;
    private final List<ValidationError> errors;
    private final Instant timestamp;
}
