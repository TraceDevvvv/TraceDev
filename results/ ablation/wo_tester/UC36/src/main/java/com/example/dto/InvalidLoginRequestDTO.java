package com.example.dto;

/**
 * Data Transfer Object for an invalid login request.
 * Contains username, password, and error message.
 */
public class InvalidLoginRequestDTO {
    private String username;
    private String password;
    private String errorMessage;

    public InvalidLoginRequestDTO(String username, String password, String errorMessage) {
        this.username = username;
        this.password = password;
        this.errorMessage = errorMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}