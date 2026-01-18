package com.example.strategy;

/**
 * Strategy interface for validation.
 */
public interface ValidationStrategy {
    boolean isValid(String data);
    String getErrorMessage();
}