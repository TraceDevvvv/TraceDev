package com.example.application_layer;

import java.util.Map;

/**
 * Application Layer: Result object returned after use case execution.
 * Contains success status, message, validation errors, and affected rest point ID.
 */
public class EditRestPointResult {
    public boolean success;
    public String message;
    public Map<String, String> validationErrors;
    public String restPointId;

    public EditRestPointResult(boolean success, String message,
                               Map<String, String> validationErrors, String restPointId) {
        this.success = success;
        this.message = message;
        this.validationErrors = validationErrors;
        this.restPointId = restPointId;
    }
}