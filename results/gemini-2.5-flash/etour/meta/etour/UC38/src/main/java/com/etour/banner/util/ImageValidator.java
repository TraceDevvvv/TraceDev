package com.etour.banner.util;

import com.etour.banner.exception.InvalidImageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for validating image characteristics of uploaded banner files.
 * This includes checking file type, size, and dimensions against configured limits.
 */
@Component
public class ImageValidator {

    // Configurable properties for image validation
    @Value("${banner.image.allowed-types}")
    private String[] allowedImageTypes; // e.g., "image/jpeg,image/png,image/gif"

    @Value("${banner.image.max-size-kb}")
    private long maxImageSizeKb; // Maximum image size in kilobytes

    @Value("${banner.image.min-width}")
    private int minWidth; // Minimum allowed width in pixels

    @Value("${banner.image.max-width}")
    private int maxWidth; // Maximum allowed width in pixels

    @Value("${banner.image.min-height}")
    private int minHeight; // Minimum allowed height in pixels

    @Value("${banner.image.max-height}")
    private int maxHeight; // Maximum allowed height in pixels

    /**
     * Validates the given MultipartFile against predefined image criteria.
     *
     * @param imageFile The image file to validate.
     * @throws InvalidImageException If the image file is null, empty, or fails any validation check.
     */
    public void validate(MultipartFile imageFile) throws InvalidImageException {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new InvalidImageException("Image file cannot be empty.");
        }

        // Validate file type
        if (!isValidFileType(imageFile.getContentType())) {
            throw new InvalidImageException("Invalid image file type. Allowed types are: " + String.join(", ", allowedImageTypes));
        }

        // Validate file size
        if (!isValidSize(imageFile.getSize())) {
            throw new InvalidImageException("Image file size exceeds the maximum allowed limit of " + maxImageSizeKb + "KB.");
        }

        // Validate dimensions
        try {
            if (!isValidDimensions(imageFile)) {
                throw new InvalidImageException(String.format(
                        "Invalid image dimensions. Image must be between %dx%d and %dx%d pixels.",
                        minWidth, minHeight, maxWidth, maxHeight
                ));
            }
        } catch (IOException e) {
            throw new InvalidImageException("Could not read image dimensions. " + e.getMessage(), e);
        }
    }

    /**
     * Checks if the given file type is among the allowed types.
     *
     * @param fileType The MIME type of the file.
     * @return true if the file type is allowed, false otherwise.
     */
    private boolean isValidFileType(String fileType) {
        if (fileType == null) {
            return false;
        }
        List<String> allowedTypesList = Arrays.asList(allowedImageTypes);
        return allowedTypesList.contains(fileType.toLowerCase());
    }

    /**
     * Checks if the given file size is within the allowed limits.
     *
     * @param fileSize The size of the file in bytes.
     * @return true if the file size is valid, false otherwise.
     */
    private boolean isValidSize(long fileSize) {
        // Convert maxImageSizeKb to bytes
        long maxImageSizeBytes = maxImageSizeKb * 1024;
        return fileSize > 0 && fileSize <= maxImageSizeBytes;
    }

    /**
     * Checks if the image dimensions (width and height) are within the allowed limits.
     *
     * @param imageFile The MultipartFile representing the image.
     * @return true if the dimensions are valid, false otherwise.
     * @throws IOException If an error occurs while reading image data.
     */
    private boolean isValidDimensions(MultipartFile imageFile) throws IOException {
        try (var inputStream = imageFile.getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage == null) {
                // ImageIO.read returns null if no reader is found or content is not an image
                throw new IOException("Could not decode image. File might be corrupted or not a valid image format.");
            }
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            return width >= minWidth && width <= maxWidth &&
                   height >= minHeight && height <= maxHeight;
        }
    }
}