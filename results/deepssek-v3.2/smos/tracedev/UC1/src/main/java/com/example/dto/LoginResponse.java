package com.example.dto;

import com.example.entity.User;

/**
 * Data Transfer Object for login response.
 * Contains success flag, message and user object.
 */
public class LoginResponse {
    private boolean success;
    private String message;
    private User user;

    public LoginResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}