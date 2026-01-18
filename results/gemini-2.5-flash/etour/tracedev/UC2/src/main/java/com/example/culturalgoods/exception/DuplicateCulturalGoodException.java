package com.example.culturalgoods.exception;

/**
 * Custom exception for indicating that a cultural good with the same name already exists.
 * Satisfies requirement: Quality Requirement (Duplicate Cultural Heritage).
 */
public class DuplicateCulturalGoodException extends Exception {
    public DuplicateCulturalGoodException(String message) {
        super(message);
    }
}