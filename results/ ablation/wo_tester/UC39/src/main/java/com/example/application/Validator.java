package com.example.application;

import com.example.infrastructure.ImageData;

/**
 * Validator class for sequence diagram message m26.
 * This is a new class to resolve missing participants.
 */
public class Validator {
    
    /**
     * Validate and return false
     * As per m26: return from Validator to UseCase
     */
    public boolean validate(ImageData imageData) {
        return false; // Always returns false as per sequence diagram
    }
}