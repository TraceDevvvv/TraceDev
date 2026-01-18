package com.example.app.dtos;

import java.util.Collections;
import java.util.List;

/**
 * DTO for user creation results.
 * Carries feedback from the application layer back to the presentation layer.
 */
public class UserCreationResultDTO {
    public boolean success;
    public List<String> validationErrors;
    public String createdUserLogin;

    /**
     * Constructor for a successful user creation.
     * @param createdUserLogin The login of the newly created user.
     */
    public UserCreationResultDTO(String createdUserLogin) {
        this.success = true;
        this.validationErrors = Collections.emptyList();
        this.createdUserLogin = createdUserLogin;
    }

    /**
     * Constructor for a failed user creation due to validation errors.
     * @param validationErrors A list of error messages.
     */
    public UserCreationResultDTO(List<String> validationErrors) {
        this.success = false;
        this.validationErrors = validationErrors;
        this.createdUserLogin = null;
    }
}