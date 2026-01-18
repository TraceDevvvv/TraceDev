package com.example.modifybanner.util;

import com.example.modifybanner.exception.InvalidImageException;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for validating image files.
 * Checks for file size, content type, and image dimensions.
 */
@Component
public class ImageValidator {

    // Configurable properties for image validation
    private static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024; // 5 MB
    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 100;
    private static final int MAX_WIDTH = 2000;
    private static final int MAX_HEIGHT = 2000;

    /**
     * Validates the given MultipartFile as an image.
     *
     * @param imageFile The MultipartFile to validate.
     * @throws InvalidImageException If the image file is null, empty, too large, has an invalid content type,
     *                               or invalid dimensions.
     */
    public void validateImage(MultipartFile imageFile) throws InvalidImageException {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new InvalidImageException("Image file cannot be empty.");
        }

        // Validate file size
        if (imageFile.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new InvalidImageException("Image file size exceeds the maximum allowed limit of " + (MAX_FILE_SIZE_BYTES / (1024 * 1024)) + "MB.");
        }

        // Validate content type
        String contentType = imageFile.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            throw new InvalidImageException("Invalid image file type. Allowed types are: " + String.join(", ", ALLOWED_CONTENT_TYPES));
        }

        // Validate image dimensions using Apache Commons Imaging
        try {
            ImageInfo imageInfo = Imaging.getImageInfo(imageFile.getBytes());
            if (imageInfo == null) {
                throw new InvalidImageException("Could not read image information.");
            }

            int width = imageInfo.getWidth();
            int height = imageInfo.getHeight();

            if (width < MIN_WIDTH || height < MIN_HEIGHT) {
                throw new InvalidImageException(String.format("Image dimensions are too small. Minimum dimensions are %dx%d pixels.", MIN_WIDTH, MIN_HEIGHT));
            }
            if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                throw new InvalidImageException(String.format("Image dimensions are too large. Maximum dimensions are %dx%d pixels.", MAX_WIDTH, MAX_HEIGHT));
            }

        } catch (IOException | ImageReadException e) {
            throw new InvalidImageException("Failed to read image characteristics: " + e.getMessage(), e);
        }
    }
}