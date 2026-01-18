package com.example.tourist;

/**
 * Custom exception for site display errors.
 */
public class SiteDisplayException extends Exception {
    public SiteDisplayException(String message) {
        super(message);
    }
}