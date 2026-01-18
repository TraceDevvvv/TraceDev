package com.example.news.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Generic API Response DTO for consistent communication back to the client.
 */
public class ApiResponse {
    public boolean success;
    public String message;
    public Object data;
    public List<String> errors;

    public ApiResponse() {
        this.errors = new ArrayList<>();
    }

    public ApiResponse(boolean success, String message, Object data, List<String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors != null ? new ArrayList<>(errors) : new ArrayList<>();
    }

    /**
     * Static factory method for a successful response.
     * @param message A success message.
     * @param data The data payload.
     * @return An ApiResponse instance indicating success.
     */
    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(true, message, data, null);
    }

    /**
     * Static factory method for a successful response with only a message.
     * @param message A success message.
     * @return An ApiResponse instance indicating success.
     */
    public static ApiResponse success(String message) {
        return new ApiResponse(true, message, null, null);
    }

    /**
     * Static factory method for an error response.
     * @param message An error message.
     * @param errors A list of detailed error messages.
     * @return An ApiResponse instance indicating failure.
     */
    public static ApiResponse error(String message, List<String> errors) {
        return new ApiResponse(false, message, null, errors);
    }

    /**
     * Static factory method for an error response with a single error message.
     * @param message An error message.
     * @return An ApiResponse instance indicating failure.
     */
    public static ApiResponse error(String message) {
        List<String> errorList = new ArrayList<>();
        if (message != null && !message.trim().isEmpty()) {
            errorList.add(message);
        }
        return new ApiResponse(false, message, null, errorList);
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    // Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors != null ? new ArrayList<>(errors) : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse that = (ApiResponse) o;
        return success == that.success &&
               Objects.equals(message, that.message) &&
               Objects.equals(data, that.data) &&
               Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, data, errors);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
               "success=" + success +
               ", message='" + message + '\'' +
               ", data=" + data +
               ", errors=" + errors +
               '}';
    }
}