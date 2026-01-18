package com.example.banner.exception;

/**
 * Custom exception for banner-related errors.
 */
public class BannerException extends Exception {
    public BannerException(String message) {
        super(message);
    }

    public BannerException(String message, Throwable cause) {
        super(message, cause);
    }
}