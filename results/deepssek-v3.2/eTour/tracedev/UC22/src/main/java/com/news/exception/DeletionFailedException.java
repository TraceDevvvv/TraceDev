package com.news.exception;

/**
 * Exception for deletion failures (though not directly used in the sequence, included per class diagram).
 */
public class DeletionFailedException extends Exception {
    private String message;

    public DeletionFailedException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}