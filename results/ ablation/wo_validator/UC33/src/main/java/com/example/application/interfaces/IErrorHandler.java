package com.example.application.interfaces;

import com.example.application.dto.ValidationResult;

/**
 * Abstract error handler.
 */
public interface IErrorHandler {
    void handle(ValidationResult error);
}