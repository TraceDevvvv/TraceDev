package com.system.dto;

import com.system.domain.ValidationResult;

/**
 * Data Transfer Object for password change response.
 */
public class ChangeResponse {
    public ValidationResult result;
    public String message;

    public ChangeResponse() {}

    public ChangeResponse(ValidationResult result, String message) {
        this.result = result;
        this.message = message;
    }
}