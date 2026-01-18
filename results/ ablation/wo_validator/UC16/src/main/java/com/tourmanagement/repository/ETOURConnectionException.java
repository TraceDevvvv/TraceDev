package com.tourmanagement.repository;

public class ETOURConnectionException extends RuntimeException {
    public ETOURConnectionException(String message) {
        super(message);
    }

    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}