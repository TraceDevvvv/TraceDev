package com.example.model;

/**
 * Generic result container for operations.
 */
public class Result {
    private boolean success;
    private String message;
    private Address address;

    // Constructors
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.address = null;
    }

    public Result(boolean success, String message, Address address) {
        this.success = success;
        this.message = message;
        this.address = address;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Address getAddress() {
        return address;
    }
}