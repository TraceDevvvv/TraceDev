package com.etour.dto;

import java.util.Date;

/**
 * Data Transfer Object for error messages.
 */
public class ErrorMessageDTO {
    private int code;
    private String message;
    private Date timestamp;

    public ErrorMessageDTO(int code, String message, Date timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorMessageDTO [code=" + code + ", message=" + message + ", timestamp=" + timestamp + "]";
    }
}