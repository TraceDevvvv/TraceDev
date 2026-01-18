package com.example;

import java.util.Map;

/**
 * Data Transfer Object for editing a user.
 */
public class EditUserDTO {
    private String id;
    private String name;
    private String email;
    private Map<String, Object> changedFields;

    public EditUserDTO(String id, String name, String email, Map<String, Object> changedFields) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.changedFields = changedFields;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Object> getChangedFields() {
        return changedFields;
    }

    /**
     * Validates the DTO fields.
     * For simplicity, checks that id is not null and email format is valid.
     */
    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();
        if (id == null || id.trim().isEmpty()) {
            result.addError("User ID is required.");
        }
        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            result.addError("Invalid email format.");
        }
        return result;
    }
}