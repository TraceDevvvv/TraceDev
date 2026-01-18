package com.example.culturalobjects;

/**
 * Data Transfer Object (DTO): Represents an error encountered during an operation.
 * Added to satisfy requirement REQ-009, providing a structured way to convey error information.
 */
public class ErrorResultDTO {
    private String errorCode;
    private String message;

    /**
     * Constructor for ErrorResultDTO.
     * @param errorCode A specific code identifying the type of error.
     * @param message A human-readable message describing the error.
     */
    public ErrorResultDTO(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResultDTO{" +
               "errorCode='" + errorCode + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}