package com.system.ui;

/**
 * Represents a transaction confirmation message.
 */
public class TransactionConfirmation {
    private String message;

    public TransactionConfirmation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}