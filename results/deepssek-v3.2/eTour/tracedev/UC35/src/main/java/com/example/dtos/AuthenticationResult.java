package com.example.dtos;

import com.example.domain.User;

/**
 * Data transfer object for authentication result.
 */
public class AuthenticationResult {
    private boolean success;
    private User user;
    private String failureReason;

    public AuthenticationResult(boolean success, User user, String failureReason) {
        this.success = success;
        this.user = user;
        this.failureReason = failureReason;
    }

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getFailureReason() {
        return failureReason;
    }
}