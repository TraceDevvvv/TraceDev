package com.example.model;

import java.util.Objects;

/**
 * Generic response DTO used for controller responses.
 */
public class ResponseDTO {
    public boolean success;
    public String message;
    public String confirmationId;
    public Object data;

    public ResponseDTO() {}

    public ResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDTO(boolean success, String message, String confirmationId) {
        this.success = success;
        this.message = message;
        this.confirmationId = confirmationId;
    }

    public ResponseDTO(boolean success, String message, String confirmationId, Object data) {
        this.success = success;
        this.message = message;
        this.confirmationId = confirmationId;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDTO that = (ResponseDTO) o;
        return success == that.success &&
                Objects.equals(message, that.message) &&
                Objects.equals(confirmationId, that.confirmationId) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, confirmationId, data);
    }
}