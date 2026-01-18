package com.example.exception;

/**
 * Interface for error detection.
 */
public interface ErrorDetector {
    /**
     * Determines if an error condition exists for the given search tags.
     * @param searchTags The search tags string.
     * @return true if error condition exists, false otherwise.
     */
    boolean isErrorCondition(String searchTags);
}