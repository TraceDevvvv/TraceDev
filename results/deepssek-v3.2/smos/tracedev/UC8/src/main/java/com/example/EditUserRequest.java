package com.example;

import java.util.Map;

/**
 * Request object for editing a user.
 */
public class EditUserRequest {
    private String userId;
    private Map<String, Object> changedFields;

    public EditUserRequest(String userId, Map<String, Object> changedFields) {
        this.userId = userId;
        this.changedFields = changedFields;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Object> getChangedFields() {
        return changedFields;
    }
}