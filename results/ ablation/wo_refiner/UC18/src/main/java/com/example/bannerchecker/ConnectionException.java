package com.example.bannerchecker;

/**
 * Custom exception to represent connection failures as per REQ-010.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}