package com.example.dto;

/**
 * Simplified response entity mimicking Spring's ResponseEntity.
 */
public class ResponseEntity<T> {
    private int status;
    private T body;

    public ResponseEntity(int status, T body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }
}