
package com.example.banner.validation;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for validating image characteristics.
 */
public class ImageValidator {

    // Max allowed image size in bytes (e.g., 5MB)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    // Allowed image formats
    private static final String[] ALLOWED_FORMATS = {"jpg", "jpeg", "png", "gif"};

    /**
     * Validates the image file.
     * @param imageFile the image file to validate
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateImage(File imageFile) throws IllegalArgumentException {
        if (imageFile == null) {
            throw new IllegalArgumentException("Image file is null.");
        }
        if (!imageFile.exists()) {
            throw new IllegalArgumentException("Image file does not exist.");
        }
        if (imageFile.length() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Image size exceeds the limit of 5MB.");
        }

        // Check file extension
        String fileName = imageFile.getName();
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1).toLowerCase();
        }
        boolean formatOk = false;
        for (String fmt : ALLOWED_FORMATS) {
            if (fmt.equals(extension)) {
                formatOk = true;
                break;
            }
        }
        if (!formatOk) {
            throw new IllegalArgumentException("Image format not allowed. Allowed: jpg, jpeg, png, gif.");
        }

        // Try to read the image to verify it's a valid image file
        try {
            BufferedImage img = ImageIO.read(imageFile);
            if (img == null) {
                throw new IllegalArgumentException("File is not a valid image.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read image file.", e);
        }
    }
}
