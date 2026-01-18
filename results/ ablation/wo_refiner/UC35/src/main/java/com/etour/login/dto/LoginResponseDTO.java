package com.etour.login.dto;

/**
 * Data Transfer Object for login response.
 * Contains success flag, user ID, and error message.
 */
public class LoginResponseDTO {
    private boolean success;
    private String userId;
    private String errorMessage;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(boolean success, String userId) {
        this.success = success;
        this.userId = userId;
    }

    public LoginResponseDTO(boolean success, String userId, String errorMessage) {
        this.success = success;
        this.userId = userId;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}