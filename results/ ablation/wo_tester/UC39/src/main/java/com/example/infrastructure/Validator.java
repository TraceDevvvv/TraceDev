package com.example.infrastructure;

/**
 * Validator interface for sequence diagram message m26.
 * This is a new class to resolve missing participants.
 */
public interface Validator {
    /**
     * Validate something and return false as per m26
     */
    boolean validate(Object obj);
}