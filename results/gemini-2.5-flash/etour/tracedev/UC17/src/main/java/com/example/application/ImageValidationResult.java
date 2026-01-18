package com.example.application;

/**
 * Enumeration representing the result of an image validation process.
 * Used by ImageValidator and ChangeBannerImageService.
 */
public enum ImageValidationResult {
    /** Image is valid according to all specified characteristics. */
    VALID,
    /** Image has invalid characteristics (e.g., wrong size, format, content). */
    INVALID_CHARACTERISTICS,
    /** An error occurred during the validation process itself (e.g., file corruption, internal system error). */
    ERROR
}