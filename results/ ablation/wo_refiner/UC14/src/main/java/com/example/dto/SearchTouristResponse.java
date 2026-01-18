package com.example.dto;

import java.util.List;

/**
 * Response DTO for tourist search.
 */
public class SearchTouristResponse {
    private List<Object> touristAccounts;
    private boolean success;
    private String errorMessage;

    public List<Object> getTouristAccounts() {
        return touristAccounts;
    }

    public void setTouristAccounts(List<Object> accounts) {
        this.touristAccounts = accounts;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }
}