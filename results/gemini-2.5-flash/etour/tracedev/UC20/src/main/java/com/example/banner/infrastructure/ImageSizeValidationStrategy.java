package com.example.banner.infrastructure;

/**
 * A concrete implementation of {@link IImageValidationStrategy} that validates
 * the size of an image.
 * This is a placeholder and in a real scenario would check against min/max sizes.
 */
public class ImageSizeValidationStrategy implements IImageValidationStrategy {
    private static final long MAX_IMAGE_SIZE_BYTES = 5 * 1024 * 1024; // 5 MB

    /**
     * Validates the image data based on its size.
     *
     * @param imageData The raw binary data of the image.
     * @return True if the image size is within acceptable limits, false otherwise.
     */
    @Override
    public boolean validate(byte[] imageData) {
        if (imageData == null || imageData.length == 0) {
            System.out.println("[ImageSizeValidationStrategy] Validating image size: Failed (empty data)");
            return false;
        }
        boolean isValid = imageData.length <= MAX_IMAGE_SIZE_BYTES;
        System.out.println("[ImageSizeValidationStrategy] Validating image size (" + imageData.length + " bytes): " + (isValid ? "OK" : "Failed (too large)"));
        return isValid;
    }
}