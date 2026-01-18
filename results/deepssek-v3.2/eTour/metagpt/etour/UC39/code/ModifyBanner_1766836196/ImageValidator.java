package com.example.banner;

/**
 * ImageValidator.java
 * Utility class to validate image characteristics such as file extension,
 * file size, and image dimensions.
 * Throws ImageValidationException if any validation fails.
 */
public class ImageValidator {

    // Constants for validation limits
    public static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024; // 5 MB
    public static final int MIN_WIDTH = 100;
    public static final int MIN_HEIGHT = 100;
    public static final int MAX_WIDTH = 2000;
    public static final int MAX_HEIGHT = 2000;

    // Allowed image file extensions
    private static final String[] ALLOWED_EXTENSIONS = {
        ".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp"
    };

    /**
     * Validates the given image file path and characteristics.
     * This method performs multiple validation steps:
     * 1. File extension validation
     * 2. File size validation (simulated)
     * 3. Image dimensions validation (simulated)
     *
     * @param imagePath Path to the image file
     * @param fileSize  Size of the image file in bytes (simulated, in real scenario would be obtained from file)
     * @param width     Image width in pixels (simulated, in real scenario would be read from image metadata)
     * @param height    Image height in pixels (simulated, in real scenario would be read from image metadata)
     * @throws ImageValidationException if validation fails with detailed message
     */
    public void validate(String imagePath, long fileSize, int width, int height) throws ImageValidationException {
        // Step 1: Validate file path is not empty
        if (imagePath == null || imagePath.trim().isEmpty()) {
            throw new ImageValidationException("Image path cannot be null or empty.");
        }

        // Step 2: Validate file extension
        validateExtension(imagePath);

        // Step 3: Validate file size
        validateFileSize(fileSize);

        // Step 4: Validate image dimensions
        validateDimensions(width, height);
    }

    /**
     * Validates the file extension of the image.
     * Checks if the file extension is in the list of allowed extensions.
     *
     * @param imagePath Path to the image file
     * @throws ImageValidationException if the file extension is not allowed
     */
    private void validateExtension(String imagePath) throws ImageValidationException {
        String lowerPath = imagePath.toLowerCase();
        boolean valid = false;
        for (String ext : ALLOWED_EXTENSIONS) {
            if (lowerPath.endsWith(ext)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new ImageValidationException(
                "Invalid image format. Allowed formats: " + String.join(", ", ALLOWED_EXTENSIONS)
            );
        }
    }

    /**
     * Validates the file size.
     * Ensures the file size does not exceed the maximum allowed size.
     *
     * @param fileSize Size of the image file in bytes
     * @throws ImageValidationException if the file size exceeds the limit
     */
    private void validateFileSize(long fileSize) throws ImageValidationException {
        if (fileSize <= 0) {
            throw new ImageValidationException("File size must be greater than zero.");
        }
        if (fileSize > MAX_FILE_SIZE_BYTES) {
            throw new ImageValidationException(
                "File size exceeds maximum allowed size of " + MAX_FILE_SIZE_BYTES + " bytes."
            );
        }
    }

    /**
     * Validates the image dimensions.
     * Ensures the width and height are within the allowed minimum and maximum.
     *
     * @param width  Image width in pixels
     * @param height Image height in pixels
     * @throws ImageValidationException if dimensions are out of bounds
     */
    private void validateDimensions(int width, int height) throws ImageValidationException {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new ImageValidationException(
                "Image dimensions too small. Minimum dimensions: " + MIN_WIDTH + "x" + MIN_HEIGHT
            );
        }
        if (width > MAX_WIDTH || height > MAX_HEIGHT) {
            throw new ImageValidationException(
                "Image dimensions too large. Maximum dimensions: " + MAX_WIDTH + "x" + MAX_HEIGHT
            );
        }
        // Optionally, check aspect ratio or other constraints
    }

    /**
     * Custom exception class for image validation errors.
     */
    public static class ImageValidationException extends Exception {
        public ImageValidationException(String message) {
            super(message);
        }
    }
}