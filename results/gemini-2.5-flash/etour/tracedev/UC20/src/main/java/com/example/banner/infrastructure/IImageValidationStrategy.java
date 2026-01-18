package com.example.banner.infrastructure;

/**
 * Interface for image validation strategies.
 * Allows different validation rules (e.g., size, format, content) to be applied.
 */
public interface IImageValidationStrategy {
    /**
     * Validates the given image data.
     *
     * @param imageData The raw binary data of the image.
     * @return True if the image data passes validation, false otherwise.
     */
    boolean validate(byte[] imageData); // SD: m25
}