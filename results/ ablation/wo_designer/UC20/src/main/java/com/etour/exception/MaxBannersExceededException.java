package com.etour.exception;

/**
 * Exception thrown when a refreshment point already has maximum banners allowed.
 */
public class MaxBannersExceededException extends Exception {
    public MaxBannersExceededException(String message) {
        super(message);
    }
}