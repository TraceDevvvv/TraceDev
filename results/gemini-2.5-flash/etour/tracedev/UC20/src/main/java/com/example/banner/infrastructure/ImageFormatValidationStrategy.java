package com.example.banner.infrastructure;

/**
 * A concrete implementation of {@link IImageValidationStrategy} that validates
 * the format of an image.
 * This is a placeholder and in a real scenario would analyze image headers.
 */
public class ImageFormatValidationStrategy implements IImageValidationStrategy {
    /**
     * Validates the image data based on its format.
     *
     * @param imageData The raw binary data of the image.
     * @return Always returns true in this dummy implementation,
     *         simulating a successful format validation.
     */
    @Override
    public boolean validate(byte[] imageData) {
        // In a real application, this would analyze imageData to determine format
        // For simplicity, we assume it's valid if not empty.
        boolean isValid = imageData != null && imageData.length > 0;
        System.out.println("[ImageFormatValidationStrategy] Validating image format: " + (isValid ? "OK" : "Failed (empty data)"));
        return isValid;
    }
}